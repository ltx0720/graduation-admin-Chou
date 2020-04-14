package com.example.authorization.utils;

import com.example.authorization.exception.RSAException.RSAException;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author ltx
 * @Date 16:45 2020/4/12
 */
@Component
public class RSAUtils {
    // 私钥解密
    public static String decryptByPrivateKey(String str, String privateKey) throws RSAException {
        // 先 Base64 解码
        byte[] inputByte = Base64.decode(str);
        byte[] key = Base64.decode(privateKey);
        RSAPrivateKey priKey = null;
        String result = "";

        try {
            priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(key));
            // RSA解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            result = new String(cipher.doFinal(inputByte));
        } catch (Exception e) {
            throw new RSAException("解密出错");
        }

        return result;
    }


    // 公钥加密
    public static String encryptByPublicKey(String str, String publicKey) throws RSAException {
        //base64编码的公钥
        byte[] decoded = Base64.decode(publicKey);
        RSAPublicKey pubKey = null;
        Cipher cipher = null;
        String result = "";

        try {
            pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            result = Base64.encode(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RSAException("加密出错");
        }

        return result;
    }
}
