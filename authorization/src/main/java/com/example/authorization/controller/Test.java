package com.example.authorization.controller;

import com.example.authorization.pojo.TokenDetail;
import com.example.authorization.service.TokenService;
import com.example.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.soap.Detail;
import java.util.Calendar;

/**
 * @Author ltx
 * @Date 15:11 2020/5/7
 */
@Controller
@RequestMapping("/test")
public class Test {

    @Autowired
    UserService service;

    @Autowired
    TokenService service1;

    @RequestMapping("/1")
    @ResponseBody
    public String test(){
        String token = "eyJ0eXBlIjoiSldUIiwiYWxnb3JpdGhtIjoibWQ1In0=.eyJ1c2VyIjp7ImlkIjo2LCJyb2xlcyI6MCwic2Nob29sX2lkIjoxLCJkZXBhcnRtZW50X2lkIjowLCJpZGVudGlmeV9pZCI6MTYxMTA1NzIwMjIsIm5hbWUiOiLliJjlpKnpnIQiLCJwaG9uZSI6IjE3ODUzMzE4MDI1IiwibWFpbCI6Imx0eDA3MjBAZ21haWwuY29tIn0sInRva2VuX2RldGFpbCI6eyJpc3N1ZSI6MTU4OTQ2NDAzNDk2MSwiYWxpdmUiOjg2NDAwfX0=.20c97529dbc2053129c707871fa5d736";
        TokenDetail detail = new TokenDetail(Calendar.getInstance().getTimeInMillis(), 86400);

        try {
            boolean b = service1.writeToken(detail, token);

        }catch (Exception e){
            System.out.println(e);
        }

        return "123";
    }
}
