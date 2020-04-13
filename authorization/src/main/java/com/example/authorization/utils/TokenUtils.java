package com.example.authorization.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.authorization.pojo.Token;
import com.example.authorization.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author ltx
 * @Date 23:46 2020/4/12
 */
@Component
public class TokenUtils {

    private static String salt;
    private static String algorithm;

    /**
     * 生成 token
     * @param user 想要在载荷中返回的 User 信息
     * @return token
     */
    public static String generateToken(User user){
        String header = getHeader();
        String playload = getPlayload(user);
        String data = header + "." + playload;

        String sign = getSign(data);

        return data + "." + sign;
    }

    private static String getHeader(){
        HashMap<String, String> map = new HashMap<>(2);
        map.put("type", "JWT");
        map.put("algorithm", algorithm);

        String json = JSONObject.toJSONString(map);

        return Base64.encode(json.getBytes());
    }

    private static String getPlayload(User user){
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("token", new Token());
        map.put("user", user);
        String json = JSONObject.toJSONString(map);

        return Base64.encode(json.getBytes());
    }

    private static String getSign(String data){
        String sign = "";
        // 后续 --> 枚举
        if ("md5".equals(algorithm)){
            sign = MD5.encrypt(data, salt);
        }

        return sign;
    }

    @Value("${token.salt}")
    public void setSalt(String salt) {
        TokenUtils.salt = salt;
    }

    @Value("${token.algorithm}")
    public static void setAlgorithm(String algorithm) {
        TokenUtils.algorithm = algorithm;
    }
}
