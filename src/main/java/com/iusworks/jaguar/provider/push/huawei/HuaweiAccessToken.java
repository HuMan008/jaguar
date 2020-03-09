/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.huawei.HuaweiAccessToken
 *
 * cluries <cluries@me.com>,  December 2017
 *
 * LastModified: 12/25/17 9:50 AM
 *
 */

package com.iusworks.jaguar.provider.push.huawei;

import com.iusworks.jaguar.config.PushProperties;
import com.iusworks.jaguar.helper.ObjectHelper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class HuaweiAccessToken {

    private static final Logger logger = LoggerFactory.getLogger(HuaweiAccessToken.class);

    private static final String ACCESS_TOKEN_URL = "https://login.vmall.com/oauth2/token";
    private static final String ACCESS_TOKEN_URL_v2 = "https://oauth-login.cloud.huawei.com/oauth2/v2/token";

    private static Map<Integer, Token> tokens = new HashMap<>();

    private static final String HuaweiTokensCacheKeyInRedis = "HuaweiTokens";

    @Autowired
    private PushProperties pushProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void construct() {
        String cacheTokens = stringRedisTemplate.opsForValue().get(HuaweiTokensCacheKeyInRedis);
        if (StringUtils.isEmpty(cacheTokens)) {
            return;
        }
        try {
            Map<String, Map<String, Object>> cacheTokensMap = ObjectHelper.getObjectMapper().readValue(cacheTokens, Map.class);
            cacheTokensMap.forEach((sysmteId, tokenInfo) -> {
                Token token = new Token();
                token.setToken(tokenInfo.get("token").toString());
                String expat = tokenInfo.get("expiresAt").toString();
                token.setExpiresAt(Long.parseLong(expat));
                tokens.put(Integer.parseInt(sysmteId), token);
            });
            logger.info("Init tokens from redis:{}", tokens);
        } catch (Exception ex) {
            logger.error("{}", ex);
        }
    }


    @Scheduled(initialDelay = 2000, fixedRate = 60000)
    public void updateTokenJob() {

        pushProperties.getPushs().forEach((p) -> {
            Map<String, String> hw = p.getAndroids().get("huawei");
            if (null == hw || hw.size() < 1) {
                return;
            }

            Integer sysId = p.getSystemId();
            Token token = tokens.get(sysId);

            if (token == null || token.getExpiresAt() - 300 <= Instant.now().getEpochSecond()) {
                logger.info("Need grant token for system:{}", sysId);
                token = grant(sysId);
                logger.info("new access token for systemid:{} value:{}", sysId, token);
                if (token != null) {
                    tokens.put(sysId, token);
                    String json = ObjectHelper.jsonString(tokens);
                    stringRedisTemplate.opsForValue().set(HuaweiTokensCacheKeyInRedis, json);
                }
            }
        });
    }

    public String tokenForSystemId(Integer systemId) {
        Token token = tokens.get(systemId);
        if (null == token) {
            logger.error("Unable get huawei token for systemId:{}", systemId);
            return null;
        }

        return token.getToken();
    }


    private Token grant(int systemId) {
        Map<String, String> huaweiProperties = HuaweiHelper.huaweiProperties(pushProperties, systemId);
        if (null == huaweiProperties || huaweiProperties.size() < 1) {
            return null;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", huaweiProperties.get("appId"));
        params.put("client_secret", huaweiProperties.get("appSecret"));
        try {

            HttpResponse<JsonNode> jsonNodeHttpResponse =  null;
            if(StringUtils.isEmpty(HuaweiHelper.version(pushProperties,systemId))){
                jsonNodeHttpResponse =  Unirest.post(ACCESS_TOKEN_URL).
                        header("Content-Type", "application/x-www-form-urlencoded  ").fields(params).asJson();
            }else{
                jsonNodeHttpResponse =  Unirest.post(ACCESS_TOKEN_URL_v2).
                        header("Content-Type", "application/x-www-form-urlencoded  ").fields(params).asJson();
            }
            if (jsonNodeHttpResponse.getStatus() < 200 || jsonNodeHttpResponse.getStatus() >= 300) {
                logger.error("{}", jsonNodeHttpResponse.getStatusText());
                return null;
            }

            JSONObject jsonObject = jsonNodeHttpResponse.getBody().getObject();
            logger.info("{}", jsonNodeHttpResponse.getBody());
            Token token = new Token();

            token.setToken(jsonObject.getString("access_token"));
            token.setExpiresAt(Instant.now().getEpochSecond() + jsonObject.getInt("expires_in") - 10);

            return token;
        } catch (Exception ex) {
            logger.error("{}", ex);
        }

        return null;
    }


    class Token {

        private String token;
        private Long expiresAt;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Long getExpiresAt() {
            return expiresAt;
        }

        public void setExpiresAt(Long expiresAt) {
            this.expiresAt = expiresAt;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "token='" + token + '\'' +
                    ", expiresAt=" + expiresAt +
                    '}';
        }
    }
}
