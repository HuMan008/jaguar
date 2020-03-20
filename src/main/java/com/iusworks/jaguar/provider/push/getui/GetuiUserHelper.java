/*
 * Copyright (C) 2020.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * jaguar com.iusworks.jaguar.provider.push.getui.GetuiUserHelper
 *
 * cluries <cluries@me.com>,  三月 2020
 *
 * LastModified: 20-3-20 下午1:32
 *
 */

package com.iusworks.jaguar.provider.push.getui;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.base.impl.AliasResult;
import com.gexin.rp.sdk.http.IGtPush;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 个推用户相关
 *
 * @author think <syj247@qq.com>、
 * @date 2020-3-20、13:32
 */
public class GetuiUserHelper {

    private Logger logger = LoggerFactory.getLogger(GetuiUserHelper.class);

    IGtPush gtPush;
    String appId;
    String uid;
    String clientId;


    public GetuiUserHelper(IGtPush gtPush, String appId, String uid, String clientId) {
        this.gtPush = gtPush;
        this.appId = appId;
        this.uid = uid;
        this.clientId = clientId;
    }

    public void register() {
        ListeningExecutorService guavaExecutor = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        ListenableFuture<IAliasResult> listenableFuture = guavaExecutor.submit(new Callable<IAliasResult>() {
            @Override
            public IAliasResult call() throws Exception {
                IAliasResult aliasResult = gtPush.bindAlias(appId, uid, clientId);
                return aliasResult;
            }
        });
        Futures.addCallback(listenableFuture, new FutureCallback<IAliasResult>() {
            @Override
            public void onSuccess(@Nullable IAliasResult iAliasResult) {
                logger.debug("gt user register{}", iAliasResult.getResponse().toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                logger.error("gt user register error {}", throwable.getCause());
            }
        }, Executors.newSingleThreadExecutor());
        guavaExecutor.shutdown();
    }

}
