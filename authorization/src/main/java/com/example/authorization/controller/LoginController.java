package com.example.authorization.controller;

import com.example.authorization.pojo.Result;
import com.example.authorization.pojo.User;
import com.example.authorization.service.UserService;
import com.example.authorization.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping(path = "/publickey", method = RequestMethod.GET)
    public Result getKey() {
        return Result.success(Base64.encode(publicKey.getBytes()));
    }

    /**
     * 请求获取 token
     */
    @RequestMapping(path = "/token", method = RequestMethod.POST)
    public Result tologin(HttpServletRequest request) throws Exception {
        String ciphertext = request.getParameter("detail");

        // 私钥解密
        byte[] bytes = RSAUtils.privateDecrypt(Base64.decode(ciphertext), RSAUtils.string2PrivateKey(privateKey));
        Map map = GsonUtil.fromJson(new String(bytes), Map.class);

        // 校验密码
        User user = validateAndgetUser((String) map.get("username"), (String)map.get("password"));
        if (user == null) return Result.login_error();

        // 生成token
        String token = TokenUtil.generateToken(user);
        String key = (String) map.get("key");
        String encrypt = AESUtil.encrypt(token, key);

        return Result.success(Base64.encode(encrypt.getBytes()));
    }


    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public Result test321(HttpServletRequest request) throws Exception {
        User user = validateAndgetUser("user", "123");

        return Result.success(user);
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
