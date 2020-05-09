package com.example.authorization.utils;

import com.example.authorization.pojo.TokenDetail;
import com.example.authorization.pojo.User;
import com.example.authorization.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ltx
 * @Date 23:46 2020/4/12
 */
@Component
public class TokenUtil {

    private static String salt;
    private static String algorithm;
    private static long alive;
    @Autowired
    private static TokenService service;


    /**
     * 生成 token
     * @param user 想要在载荷中返回的 User 信息
     * @return token
     */
    public static String generateToken(User user) {
        TokenDetail detail = new TokenDetail(Calendar.getInstance().getTimeInMillis(), alive);

        String header = getHeader();
        String playload = getPlayload(user, detail);
        String data = header + "." + playload;
        String token = data + "." + getSign(data);

        // token 写库
        boolean result = service.writeToken(detail, token);

        if (!result) return null;

        return token;
    }

    /**
     * 生成 token 的 Header 部分
     * @return String
     */
    private static String getHeader(){
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("type", "JWT");
        map.put("algorithm", algorithm);

        return Base64.encode(GsonUtil.toJson(map).getBytes());
    }

    /**
     * 生成 token 的 Playload 部分
     * @return String
     */
    private static String getPlayload(User user, TokenDetail detail){
        Map<String, Object> map = new HashMap<>(2);
        map.put("token_detail", detail);
        map.put("user", user);

        return Base64.encode(GsonUtil.toJson(map).getBytes());
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
        TokenUtil.salt = salt;
    }

    @Value("${token.algorithm}")
    public void setAlgorithm(String algorithm) {
        TokenUtil.algorithm = algorithm;
    }


    @Value("${token.alive}")
    public void setAlive(long alive) {
        TokenUtil.alive = alive;
    }
}
