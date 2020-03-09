/*
 * Copyright (C) 2020.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * jaguar com.iusworks.jaguar.tools.NotifyIDUtils
 *
 * cluries <cluries@me.com>,  三月 2020
 *
 * LastModified: 20-3-6 上午10:46
 *
 */

package com.iusworks.jaguar.tools;
/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Airjet com.iusworks.airjet.common.tools.IDTool
 *
 * cluries <cluries@me.com>,  July 2017
 *
 * LastModified: 7/20/17 4:57 PM
 *
 */


public final class NotifyIDUtils {

    private NotifyIDUtils() {

    }

    public static int generatorID(String mid) {
        byte[] bytes = parse(mid);
        if (bytes.length != 12) {
            return 0;
        }

        return (((bytes[0]) << 24) | ((bytes[1] & 0xff) << 16) | ((bytes[2] & 0xff) << 8) | ((bytes[3] & 0xff))) % 10000;
    }

    private static byte[] parse(final String s) {
        if (!isValid(s)) {
            throw new IllegalArgumentException("invalid hex string : [" + s + ']');
        }

        byte[] b = new byte[12];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    private static boolean isValid(final String s) {
        if (s == null) {
            return false;
        }

        int len = s.length();
        if (len != 24) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c >= 'a' && c <= 'f') {
                continue;
            }
            if (c >= 'A' && c <= 'F') {
                continue;
            }

            return false;
        }

        return true;
    }

}

