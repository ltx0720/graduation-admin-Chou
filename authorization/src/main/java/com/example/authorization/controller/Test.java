package com.example.authorization.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.authorization.pojo.Person;
import com.example.authorization.utils.Base64;
import com.example.authorization.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author ltx
 * @Date 15:07 2020/4/12
 */
@RestController
public class Test {
    @Value("${publickey}")
    String publickey;
    @Value("${privatekey}")
    String privatekey;

    @Autowired
    RSAUtils rsaUtils;

//    @RequestMapping("/test")
//    public String test() throws Exception {
//        String a = "123";
//        String encrypt = RSAUtils.encrypt(a, publickey);
//        System.out.println(encrypt);
//
//        String decrypt = RSAUtils.decryptByPrivateKey(encrypt, privatekey);
//        System.out.println(decrypt);
//        return "";
//    }

    Map<String, Object> jsonObject = JSON.parseObject("");


    @RequestMapping("/test1")
    public String test1() throws Exception {
        Person person = new Person(1, "name");
        String string = JSONObject.toJSONString(person);
        String rsa = RSAUtils.encryptByPublicKey(string, publickey);
        System.out.println(rsa);
        String encode = Base64.encode(rsa.getBytes());
        System.out.println(encode);

        String decode = new String(Base64.decode(encode));
        System.out.println(decode);
        String decrypt = RSAUtils.decryptByPrivateKey(decode, privatekey);
        System.out.println(decode);
        Person p = JSONObject.parseObject(decrypt, Person.class);
        System.out.println(p);

        return "";
    }
    @RequestMapping("/test2")
    public String test2() throws Exception {
        Person person = new Person(1, "name");
        String jsonString = JSONObject.toJSONString(person);
        System.out.println(jsonString);
        Map<String, Object> map = JSON.parseObject(jsonString, Map.class);
        System.out.println();
        System.out.println(map.get("id"));
        System.out.println(map.get("name"));

        return "";
    }

}
