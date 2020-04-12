package com.example.authorization.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.authorization.pojo.Person;
import com.example.authorization.pojo.Result;
import com.example.authorization.pojo.Token;
import com.example.authorization.pojo.User;
import com.example.authorization.service.UserService;
import com.example.authorization.utils.Base64;
import com.example.authorization.utils.MD5;
import com.example.authorization.utils.RSAUtils;
import com.example.authorization.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
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

    @Value("${publickey}")
    private String PUBLIC_KEY;
    @Value("${privatekey}")
    private String PRIVATE_KEY;
    @Autowired
    private UserService userService;

//    @RequestMapping("/token")
//    public String getToken(){
//        return "";
//    }

    @RequestMapping(value = "/publickey", method = RequestMethod.GET)
    public String getKey() throws Exception {
        return PUBLIC_KEY;
    }

    @RequestMapping(value = "/tologin", method = RequestMethod.POST)
    public String tologin(HttpServletRequest request) {
        String detail = request.getParameter("detail");
        Map<String, Object> map = getRequestResult(detail);

        boolean signIsLegal = validateSign(map);
        if (!signIsLegal){
            // 后续 --> 枚举
            return new Result(400, "签名校验错误").toString();
        }

        boolean isExist = validateUser((User) map.get("user"));
        if (!isExist){
            return new Result(400, "用户名或密码错误").toString();
        }

        // 生成token
        Token token = TokenUtils.generateToken();

        return new Result(200, token).toString();
    }

    private Map<String, Object> getRequestResult(String data){
        // Base64解码
        String decode = new String(Base64.decode(data));
        // 私钥解密
        String decrypt = RSAUtils.decryptByPrivateKey(decode, PRIVATE_KEY);
        Map<String, Object> map = JSON.parseObject(decrypt, Map.class);

        return map;
    }

    // 校验用户名密码
    private boolean validateUser(User user){
        return userService.validate(user);
    }

    // 验签
    private boolean validateSign(Map<String, Object> map){
        User user = (User) map.get("user");
        String salt = (String)map.get("salt");
        String sign = (String)map.get("sign");

        return MD5.validateSign(user.toString(), salt, sign);
    }
}
