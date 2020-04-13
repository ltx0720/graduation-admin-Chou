package com.example.authorization.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.authorization.pojo.Result;
import com.example.authorization.pojo.User;
import com.example.authorization.service.UserService;
import com.example.authorization.utils.Base64;
import com.example.authorization.utils.MD5;
import com.example.authorization.utils.RSAUtils;
import com.example.authorization.utils.TokenUtils;
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


    @RequestMapping(value = "/publickey", method = RequestMethod.GET)
    public String getKey() throws Exception {
        return publicKey;
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public String tologin(HttpServletRequest request) {
        String detail = request.getParameter("detail");
        Map<String, Object> map = getRequestResult(detail);

        // 校验签名
        boolean signIsLegal = validateSign(map);
        if (!signIsLegal){
            // 后续 --> 枚举
            return new Result(400, "签名校验错误").toString();
        }

        // 校验密码
        User user = validateAndgetUser((String) map.get("username"), (String)map.get("password"));
        if (user == null){
            return new Result(400, "用户名或密码错误").toString();
        }

        // 生成token
        String token = TokenUtils.generateToken(user);

        return new Result(200, token).toString();
    }

    // 把请求中的信息解密
    private Map<String, Object> getRequestResult(String data){
        String decode = new String(Base64.decode(data));
        String decrypt = RSAUtils.decryptByPrivateKey(decode, privateKey);
        Map<String, Object> map = JSON.parseObject(decrypt, Map.class);

        return map;
    }

    // 校验用户名密码
    private User validateAndgetUser(String username, String password){
        return userService.validate(username, password);
    }

    // 验签
    private boolean validateSign(Map<String, Object> map){
        String salt = (String)map.get("salt");
        String sign = (String)map.get("sign");
        map.remove("sign");

        String json = JSONObject.toJSONString(map);

        return MD5.validateSign(json, salt, sign);
    }

    @Value("${rsa.privatekey}")
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Value("${rsa.publickey}")
    public static void setPublicKey(String publicKey) {
        LoginController.publicKey = publicKey;
    }
}
