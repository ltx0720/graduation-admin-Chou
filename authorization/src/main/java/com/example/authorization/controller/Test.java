package com.example.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author ltx
 * @Date 15:11 2020/5/7
 */
@Controller
public class Test {

    @RequestMapping("/authentication/form")
    @ResponseBody
    public String test(){


        return "123";
    }
}
