package com.example.authorization.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import com.dyuproject.protostuff.ProtostuffIOUtil;
import javax.sql.DataSource;

/**
 * @Author ltx
 * @Date 17:58 2020/4/9
 *
 * 认证相关接口
 */
@RestController
@RequestMapping("/identify")
public class LoginController {

    @RequestMapping("/token")
    public String getToken(){

        return "";
    }

    @RequestMapping("/key")
    public String getKey(){

        return "";
    }
}
