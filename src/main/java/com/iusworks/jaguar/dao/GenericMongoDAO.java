/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.dao.GenericMongoDAO
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/25/16 10:34 PM
 *
 */

package com.iusworks.jaguar.dao;

import com.mongodb.WriteResult;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.beans.PropertyDescriptor;
import java.util.Set;

public class GenericMongoDAO<T> {


    private static Logger logger = LoggerFactory.getLogger(GenericMongoDAO.class);

    @Autowired
    protected MongoTemplate mongoTemplate;


    /**
     * @param entity
     * @param queryKeys
     * @param excludeUpdateKeys
     * @return
     */
    public boolean upsert(T entity, Set<String> queryKeys, Set<String> excludeUpdateKeys) {

        if (queryKeys == null || queryKeys.size() < 1) {
            throw new GenericMongoDAOException("Please use insert!");
        }


        Criteria criteria = null;
        for (String key : queryKeys) {
            try {
                Object obj = PropertyUtils.getProperty(entity, key);
                if (criteria == null) {
                    criteria = Criteria.where(key).is(obj);
                } else {
                    criteria.and(key).is(obj);
                }
            } catch (Exception ex) {
                logger.error("{}", ex);
            }
        }


        Query query = Query.query(criteria);
        Update update = new Update();


        for (PropertyDescriptor descriptor : PropertyUtils.getPropertyDescriptors(entity.getClass())) {
            try {
                String name = descriptor.getName();
                if ("class".equals(name)) {
                    continue;
                }

                if (excludeUpdateKeys != null && excludeUpdateKeys.contains(name)) {
                    continue;
                }

                Object obj = descriptor.getReadMethod().invoke(entity);
                if ("id".equals(name)) {
                    if (obj == null) {
                        continue;
                    }
                }
                
                update.set(name, obj);
//                logger.debug("update: --- {}   {}", name, obj);
            } catch (Exception ex) {
                logger.error("{}", ex);
            }
        }

        WriteResult writeResult = mongoTemplate.upsert(query, update, entity.getClass());
        return writeResult != null && writeResult.getN() > 0;
    }


    public void insert(T entity) {
        mongoTemplate.insert(entity);
    }

//    public int remove(T entity) {
//        WriteResult writeResult = mongoTemplate.remove(entity);
//        return writeResult.getN();
//    }
//
//    private int remove(Map<String, Object> querys) {
//        Criteria criteria = null;
//        for (String key : querys.keySet()) {
//            try {
//
//                if (criteria == null) {
//                    criteria = Criteria.where(key).is(querys.get(key));
//                } else {
//                    criteria.and(key).is(querys.get(key));
//                }
//            } catch (Exception ex) {
//                logger.error("{}", ex);
//            }
//        }
//
//        Class clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericMongoDAO.class);
//        WriteResult writeResult = mongoTemplate.remove(querys, clazz.getClass());
//        return writeResult.getN();
//    }

}
