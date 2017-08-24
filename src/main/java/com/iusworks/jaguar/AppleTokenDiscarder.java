/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.AppleTokenDiscarder
 *
 * cluries <cluries@me.com>,  August 2017
 *
 * LastModified: 8/23/17 2:37 PM
 *
 */

package com.iusworks.jaguar;


import com.iusworks.jaguar.dao.DeviceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class AppleTokenDiscarder {

    private static Logger logger = LoggerFactory.getLogger(AppleTokenDiscarder.class);

    @Autowired
    private DeviceDAO deviceDAO;

    private static ConcurrentLinkedQueue<DiscardPair> discardPairConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    public static void appendDiscardQueue(String deviceId, String unefficacyToken) {
        if (StringUtils.isEmpty(deviceId)) {
            logger.error("Empty DeviceId for AppleTokenDiscarder::appendDiscardQueue");
        }

        DiscardPair pair = new DiscardPair();
        pair.setId(deviceId);
        pair.setToken(unefficacyToken);
        discardPairConcurrentLinkedQueue.add(pair);
    }

    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void discard() {
        logger.info("Start AppleTokenDiscarder..");

        while (!discardPairConcurrentLinkedQueue.isEmpty()) {
            DiscardPair pair = discardPairConcurrentLinkedQueue.peek();
            if (pair == null) {
                break;
            }

            if (StringUtils.isEmpty(pair.getId())) {
                continue;
            }

            logger.info("Discard:{}", pair);
            deviceDAO.discardVoucher(pair.getId(), pair.getToken());
        }
    }


}


class DiscardPair {
    private String id;
    private String token;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscardPair)) return false;

        DiscardPair that = (DiscardPair) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return token != null ? token.equals(that.token) : that.token == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DiscardPair{" +
                "id='" + id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}