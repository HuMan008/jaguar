/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.JaguarRunner
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/22/16 10:56 PM
 *
 */

package com.iusworks.jaguar;

import com.iusworks.jaguar.config.JaguarProperties;
import com.iusworks.jaguar.thrift.JaguarService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.InetSocketAddress;


@Component
public class JaguarRunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(JaguarRunner.class);

    @Autowired
    private JaguarService.Iface jaguarServiceFace;

    @Autowired
    private JaguarProperties jaguarProperties;

    @Override
    public void run(String... args) throws Exception {
        JaguarService.Processor jaguarServiceProcessor = new JaguarService.Processor<>(jaguarServiceFace);

        InetAddress inetAddress;

        logger.info("{}", jaguarProperties.toString());

        if (!jaguarProperties.getHost().matches("^\\d+?\\.\\d+?\\.\\d+?\\.\\d+?$")) {
            inetAddress = InetAddress.getLocalHost();
        } else {
            String[] bits = jaguarProperties.getHost().split("\\.");
            byte[] addbits = new byte[4];
            for (int i = 0; i < 4; i++) {
                addbits[i] = Short.valueOf(bits[i]).byteValue();
            }
            inetAddress = InetAddress.getByAddress(addbits);
        }
        
        InetSocketAddress socketAddress;
        try {
            socketAddress = new InetSocketAddress(inetAddress, jaguarProperties.getPort());
        } catch (Exception ex) {
            logger.error("{}", ex);
            socketAddress = new InetSocketAddress(jaguarProperties.getPort());
        }

        TServerSocket socket = new TServerSocket(socketAddress);
        TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(socket);
        serverArgs.processor(jaguarServiceProcessor);
        serverArgs.maxWorkerThreads(1024);
        serverArgs.requestTimeout = 30;
        serverArgs.protocolFactory(TBinaryProtocol::new);
        TServer tServer = new TThreadPoolServer(serverArgs);
        logger.info("Jaguar Started, Listen at : {}", socketAddress);
        tServer.serve();
    }

}