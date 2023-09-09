package com.java.coco.utils;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * hash 256
 * @author hy
 */
@Slf4j
public class SHA256HashUtil {

    private static final String ALGORITHM = "SHA-256";

    /**
     * hash256计算
     *
     * @param input
     * @return
     */
    public static String getSHA256Hash(String input) {
        MessageDigest digest;
        byte[] encodedhash = new byte[0];

        try {
            digest = MessageDigest.getInstance(ALGORITHM);
            encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getCause().toString());
        }

        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

