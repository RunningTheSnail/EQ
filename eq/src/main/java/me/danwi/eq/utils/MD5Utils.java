package me.danwi.eq.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by RunningSnail on 16/6/2.
 */
public class MD5Utils {

    private MD5Utils() {

    }

    public static String md5(String key) {
        String value;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(key.getBytes());
            value = bytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            value = String.valueOf(key.hashCode());
        }
        return value;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
