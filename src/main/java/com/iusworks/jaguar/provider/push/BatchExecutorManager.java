/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.provider.push.BatchExecutorManager
 *
 * cluries <cluries@me.com>,  October 2017
 *
 * LastModified: 10/10/17 9:37 AM
 *
 */

package com.iusworks.jaguar.provider.push;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BatchExecutorManager {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    @SuppressWarnings("all")
    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static void execute(Runnable command) {
        executorService.execute(command);
    }
}
