package com.example.service.utils;

import com.example.service.pojo.TokenDetail;
import com.example.service.pojo.User;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;

/**
 * @Author ltx
 * @Date 21:26 2020/5/8
 *
 * token 工具
 */
public class TokenUtil {
    private static String salt;
    private static String algorithm;

    public static boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return checkToken(request, token);
    }

    /**
     * 校验 token，并读取信息
     */
    private static boolean checkToken(HttpServletRequest request, String token){
        if (!checkSign(token)) return false;
        if (!isAlive(token)) return false;

        fetchData(request, token);

        return true;
    }

    /**
     * 校验签名
     *
     * @param token token
     * @return true/false
     */
    private static boolean checkSign(String token) {
        String[] split = token.split("\\.");
        String sign = split[2];

        String encrypt = MD5.encrypt(split[0] + "." + split[1], salt);

        return encrypt.equals(sign);
    }

    /**
     * 判断 token 是否过期
     *
     * @param playload token 的 playload 部分
     * @return true/false
     */
    private static boolean isAlive(String playload) {
        byte[] bytes = Base64.decode(playload);
        Map<String, Object> map = GsonUtil.fromJson(new String(bytes), Map.class);

        TokenDetail tokenDetail = (TokenDetail) map.get("token_detail");
        long current = Calendar.getInstance().getTimeInMillis();

        return current > tokenDetail.getIssue() + tokenDetail.getAlive();
    }

    /**
     * 读取 token 中信息保存到 request 中，方便后续使用
     */
    private static void fetchData(HttpServletRequest request, String token){
        String playload = token.split("\\.")[1];
        byte[] bytes = Base64.decode(playload);

        Map<String, Object> map = GsonUtil.fromJson(new String(bytes), Map.class);
        User user = (User) map.get("user");
        request.setAttribute("user", user);
    }


    @Value("${token.salt}")
    protected void setSalt(String salt) {
        TokenUtil.salt = salt;
    }

    @Value("${token.algorithm}")
    protected void setAlgorithm(String algorithm) {
        TokenUtil.algorithm = algorithm;
    }

}
