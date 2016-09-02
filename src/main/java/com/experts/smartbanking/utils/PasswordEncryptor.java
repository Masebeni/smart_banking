package com.experts.smartbanking.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by AXE on 2015/11/02.
 */

public class PasswordEncryptor {

    public static String convertPasswordToMD5(String pass) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pass.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
