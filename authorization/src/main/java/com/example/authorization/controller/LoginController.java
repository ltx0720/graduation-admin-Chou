package com.example.authorization.controller;

import com.example.authorization.exception.RSAException.RSAException;
import com.example.authorization.pojo.Result;
import com.example.authorization.pojo.User;
import com.example.authorization.service.UserService;
import com.example.authorization.utils.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ltx
 * @Date 17:58 2020/4/9
 *
 * 认证相关接口
 */
@RestController
@RequestMapping("/identify")
public class LoginController {

    private static String publicKey;
    private String privateKey;
    @Autowired
    private UserService userService;

    /**
     * 获取公钥
     * @return String 公钥
     */
    @RequestMapping(value = "/publickey", method = RequestMethod.GET)
    public String getKey() {
        return publicKey;
    }

    /**
     * 请求获取 token
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result tologin(HttpServletRequest request) {
        String detail = request.getParameter("detail");

        // 私钥解密
//        Map<String, Object> map = getRequestResult(detail);

        Map<String, String> map = new HashMap<>();
        map.put("username", "user");
        map.put("password", "123");

        // 校验密码
        User user = validateAndgetUser((String) map.get("username"), (String)map.get("password"));
        if (user == null) return Result.login_error();

        // 生成token
        String token = TokenUtil.generateToken(user);
        if (token == null) return Result.error();

        return Result.success(token);
    }


    /**
     * request 中的信息解密
     */
    private Map getRequestResult(String data){
        String decode = data;
//                String decode = new String(Base64.decode(data));
        String decrypt = null;
        try {
            decrypt = RSAUtils.decryptByPrivateKey(decode, privateKey);
        } catch (RSAException e) {
            e.printStackTrace();
        }

        return GsonUtil.fromJson(decode, Map.class);
    }


    /**
     * 校验用户名密码
     */
    private User validateAndgetUser(String username, String password){
        return userService.validate(username, password);
    }

    // 验签
//    private boolean validateSign(Map<String, Object> map){
//        String salt = (String)map.get("salt");
//        String sign = (String)map.get("sign");
//        map.remove("sign");
//
//        String json = GsonUtil.t(map, Map.class);
//      JSONObject.toJSONString(map);
//
//        return MD5.validateSign(json, salt, sign);
//    }

    @Value("${rsa.privatekey}")
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Value("${rsa.publickey}")
    public void setPublicKey(String publicKey) {
        LoginController.publicKey = publicKey;
    }
}
