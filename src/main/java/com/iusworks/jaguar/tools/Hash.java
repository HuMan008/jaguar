/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.tools.Hash
 *
 * cluries <cluries@me.com>,  October 2016
 *
 * LastModified: 9/27/16 10:46 AM
 *
 */

package com.iusworks.jaguar.tools;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hash {

    private static final String SHA1 = "SHA-1";
    private static final String MD5 = "MD5";

    public static String sha1(String str) {
        MessageDigest messageDigest = messageDigestForAlgorithm(SHA1);
        if (messageDigest == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        return ByteHexUtils.byte2hex(messageDigest.digest(bytes));
    }

    public static String md5(String str) {
        MessageDigest messageDigest = messageDigestForAlgorithm(MD5);
        if (messageDigest == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        return ByteHexUtils.byte2hex(messageDigest.digest(bytes));
    }

    private static MessageDigest messageDigestForAlgorithm(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }

//
//    public static void main(String[] args) {
//        String base = "628f07f55a8ac807b99bb532edbc5e516fdf1bed";
//        for (int i = 1; i <= 10; i++) {
//            String item = StringUtils.repeat(base, i);
//            String out = String.format("%02d\t%s\t%s", i, sha1(item), item);
//            System.out.println(out);
//        }
//    }


}

