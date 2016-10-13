/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.tools.AirHttpClient
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/30/16 2:46 PM
 *
 */

package com.iusworks.jaguar.tools;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirHttpClient {

    private static Logger logger = LoggerFactory.getLogger(AirHttpClient.class);

    public final static String InferCharset = "InferCharset";

    public final static String ParamEncodeTypeForm = "Form";
    public final static String ParamEncodeTypeJSON = "JSON";
    public final static String ParamEncodeTypeString = "STRING";


    private static final ObjectWriter objectWriter = new ObjectMapper().writer();

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final HttpClient httpClient;

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(5000)
            .setConnectTimeout(5000)
            .build();


    private static HashMap<String, ResponseHandler<String>> stringResponseHandlerHashMap = new HashMap<>();


    static {
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.disableAuthCaching();
        httpClient = clientBuilder.build();
    }

    public static Map<String, String> parseQueryString(String qs) {
        String[] groups = qs.split("&");
        Map<String, String> params = new HashMap<>();
        for (String group : groups) {

        }

        return params;
    }


    /**
     * convert json string to object
     *
     * @param string
     * @return
     */
    public static Object objectFromString(String string) {
        try {
            return mapper.readValue(string, Object.class);
        } catch (Exception ex) {
            logger.error("{}", ex);
            return null;
        }
    }

    /**
     * Post with json body
     *
     * @param url
     * @param object
     * @return
     */
    public static String POSTJSON(String url, Object object, Map<String, String> addHeaders) {
        return POST(url, object, "utf-8", "application/json", ParamEncodeTypeJSON, addHeaders);
    }

    /**
     * @param url
     * @param object
     * @param charsetName
     * @param paramsEncodeType
     * @return
     */
    public static String POST(String url, Object object, String charsetName, String contentType, String paramsEncodeType, Map<String, String> addHeaders) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        if (addHeaders != null) {
            for (String key : addHeaders.keySet()) {
                httpPost.setHeader(key, addHeaders.get(key));
            }
        }


        String response = null;
        try {
            if (ParamEncodeTypeForm.equals(paramsEncodeType)) {
                List<NameValuePair> formparams = new ArrayList<>();

                // TODO  这里有问题,没有确认Map的范型类型
                if (object instanceof Map) {
                    Map<String, String> map = (Map<String, String>) object;
                    map.forEach((k, v) -> formparams.add(new BasicNameValuePair(k, v)));
                } else {
                    formparams.add(new BasicNameValuePair("", objectWriter.writeValueAsString(object)));
                }
                UrlEncodedFormEntity formEntity;
                if (InferCharset.equals(charsetName)) {
                    formEntity = new UrlEncodedFormEntity(formparams);
                } else {
                    formEntity = new UrlEncodedFormEntity(formparams, charsetName);
                }
                httpPost.setEntity(formEntity);
            } else if (ParamEncodeTypeJSON.equals(paramsEncodeType)) {
                if (InferCharset.equals(charsetName)) {
                    charsetName = "UTF-8";
                }
                StringEntity entity = new StringEntity(objectWriter.writeValueAsString(object),
                        ContentType.create(contentType, Charset.forName(charsetName)));
                httpPost.setEntity(entity);
            } else {
                if (InferCharset.equals(charsetName)) {
                    charsetName = "UTF-8";
                }
                StringEntity entity = new StringEntity(object.toString(), ContentType.create(contentType, Charset.forName(charsetName)));
                httpPost.setEntity(entity);
            }

            response = httpClient.execute(httpPost, stringResponseHandlerByContentCharset(charsetName));
        } catch (Exception ex) {
            logger.error("{}", ex);
        }

        return response;
    }

    /**
     * @param url
     * @param params
     * @return
     */
    public static String GET(String url, Map<String, String> params) {
        return GET(url, params, InferCharset);
    }

    /**
     * @param url
     * @param params
     * @param charsetName
     * @return
     */
    public static String GET(String url, Map<String, String> params, String charsetName) {

        URI uri;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null) {
                params.forEach((k, v) -> uriBuilder.setParameter(k, v));
            }
            uri = uriBuilder.build();
        } catch (Exception ex) {
            logger.error("{}", ex);
            return null;
        }

        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(requestConfig);

        try {
            return httpClient.execute(httpGet, stringResponseHandlerByContentCharset(charsetName));
        } catch (Exception ex) {
            logger.error("{}", ex);
        }

        return null;
    }


    /**
     * @param entity
     * @param charsetName
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String contentFromHttpEntity(HttpEntity entity, String charsetName) throws UnsupportedEncodingException {
        InputStream inputStream;
        try {
            inputStream = entity.getContent();
        } catch (Exception ex) {
            logger.error("{}", ex);
            return null;
        }

        if (inputStream == null) {
            return null;
        }

        InputStreamReader inputStreamReader;
        if (InferCharset.equals(charsetName)) {
            ContentType contentType = ContentType.getOrDefault(entity);
            Charset charset = contentType.getCharset();
            if (null == charset) {
                charset = Charset.forName("UTF-8");
            }
            inputStreamReader = new InputStreamReader(inputStream, charset);
        } else {
            inputStreamReader = new InputStreamReader(inputStream, charsetName);
        }


        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        char[] charBuffer = new char[128];
        int bytesRead;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
            inputStream.close();
            bufferedReader.close();
            inputStreamReader.close();
        } catch (Exception ex) {
            logger.error("{}", ex);
        }

        return stringBuilder.toString();
    }


    /**
     * @param charsetName
     * @return
     */
    private static ResponseHandler<String> stringResponseHandlerByContentCharset(String charsetName) {
        if (!InferCharset.equals(charsetName)) {
            if (!Charset.isSupported(charsetName)) {
                logger.error("Unsupported Charset {}", charsetName);
                return null;
            }
        }


        if (stringResponseHandlerHashMap.get(charsetName) == null) {
            synchronized (stringResponseHandlerHashMap) {
                if (stringResponseHandlerHashMap.get(charsetName) == null) {
                    ResponseHandler<String> responseHandler = (response) -> {
                        StatusLine statusLine = response.getStatusLine();
                        HttpEntity entity = response.getEntity();
                        if (statusLine.getStatusCode() >= 300) {
                            logger.error("{}", contentFromHttpEntity(entity, charsetName));
                            throw new HttpResponseException(
                                    statusLine.getStatusCode(),
                                    statusLine.getReasonPhrase());
                        }

                        if (entity == null) {
                            throw new ClientProtocolException("Response contains no content");
                        }

                        return contentFromHttpEntity(entity, charsetName);
                    };

                    stringResponseHandlerHashMap.put(charsetName, responseHandler);
                }
            }
        }

        return stringResponseHandlerHashMap.get(charsetName);
    }


    /*
    private static ResponseHandler<Object> jsonResponseHandler = (response) -> {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            throw new HttpResponseException(
                    statusLine.getStatusCode(),
                    statusLine.getReasonPhrase());
        }

        if (entity == null) {
            throw new ClientProtocolException("Response contains no content");
        }

        ContentType contentType = ContentType.getOrDefault(entity);
        Charset charset = contentType.getCharset();
        Object object = mapper.readTree(entity.GET());
        return object;
    };


    public static Object POST(String url, Object object) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        try {

            StringEntity entity = new StringEntity(objectWriter.writeValueAsString(object));
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            Object response = httpClient.execute(httpPost, jsonResponseHandler);
            return response;
        } catch (Exception ex) {
            logger.error("{}", ex);
        }

        return null;
    }
    */
}
