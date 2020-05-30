package com.example.fileserver.utils;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @Author ltx
 * @Date 23:04 2020/5/22
 *
 * aes
 */
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法
    private static final String IV = "ihaierForTodo_Iv";

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.encode(result);//通过Base64转码返回
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

//    public static byte[] encrypt(String src, byte[] key) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
//        IvParameterSpec ivParameterSpec = getIv();
//        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
//        byte[] cipherBytes = cipher.doFinal(src.getBytes(Charset.forName("utf-8")));
//        return cipherBytes;
//    }
//    public static IvParameterSpec getIv() throws UnsupportedEncodingException {
//        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes("utf-8"));
//        return ivParameterSpec;
//    }
//
//    public static String byteToHexString(byte[] src) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < src.length; i++) {
//            int v = src[i] & 0xff;
//            String hv = Integer.toHexString(v);
//            if (hv.length() < 2) {
//                sb.append("0");
//            }
//            sb.append(hv);
//        }
//        return sb.toString();
//    }


    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            //执行操作
            byte[] result = cipher.doFinal(Base64.decode(content));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(key.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
