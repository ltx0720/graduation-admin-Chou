package com.example.service.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author ltx
 * @Date 21:46 2020/4/12
 */
public class MD5 {
    public static boolean validateSign(String json, String salt, String sign){
        if (json == null || salt == null || sign== null || json.equals("") || salt.equals("") || sign.equals("")) return false;

        String cipher = encrypt(json, salt);

        return sign.equals(cipher);
    }

    public static String encrypt(String data, String salt){
        String md5Hex = DigestUtils.md5Hex(data);
        return DigestUtils.md5Hex(md5Hex + salt);
    }

}
