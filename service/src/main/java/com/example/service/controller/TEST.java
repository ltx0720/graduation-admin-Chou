package com.example.service.controller;

import com.example.service.dao.common.CommonDao;
import com.example.service.dao.manager.ManagerDao;
import com.example.service.dao.student.StudentDao;
import com.example.service.pojo.*;
import com.example.service.service.common.CommonService;
import com.example.service.service.manager.ManagerService;
import com.example.service.service.student.StudentService;
import com.example.service.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ltx
 * @Date 23:38 2020/5/11
 */
@RestController
@RequestMapping("/test")
public class TEST {

    @Autowired
    CommonService service;

    @Autowired
    ManagerDao dao;

    @Autowired
    StudentDao studentDao;
    @Autowired
    ManagerService managerService;

    @RequestMapping("/dao")
    public String dsafdsa(){

        return "";
    }


    @PostMapping("/upload")
    @ResponseBody
    public String upload( MultipartFile file) {
        return "上传失败！";
    }

    @PostMapping("/test")
    @ResponseBody
    public boolean tedsa() {

        HashMap<String, Object> map = new HashMap<>(3);
        map.put("student_id", 1);
        map.put("topic_id", 1);
        map.put("result", false);
        studentDao.seletTopic(map);
        boolean b = (boolean)map.get("result");
        System.out.println(b);
        return b;
    }

    @PostMapping("/test1")
    @ResponseBody
    public Result dsada() {

        return Result.success(200,  managerService.getMenuList(1));
    }

    @PostMapping("/md")
    @ResponseBody
    public Result md5() {

        String data = "eyJ0eXBlIjoiSldUIiwiYWxnb3JpdGhtIjoibWQ1In0=.eyJ1c2VyIjp7ImlkIjowLCJyb2xlcyI6MCwic2Nob29sX2lkIjowLCJkZXBhcnRtZW50X2lkIjoxLCJpZGVudGlmeV9pZCI6MSwibmFtZSI6IuWImOWkqemchCJ9LCJ0b2tlbl9kZXRhaWwiOnsiaXNzdWUiOjE1OTA1MDY4NTIxOTgsImFsaXZlIjo2MDQ4MDAwMDB9fQ==";
        String salt = "123";

        String s1 = MD5.encrypt(data, salt);
        String s2 = MD5.encrypt(data, salt);
        return Result.success(200,  managerService.getMenuList(1));
    }

    @Autowired
    CommonDao commonDao;

    @PostMapping("/list")
    @ResponseBody
    public Result list312312() {
//        List<Menu> list = commonDao.getMenuList(1, 0);
//
        List<Map<String, Object>> list = service.getMenuList(1, 0);
        return Result.success(200,  list);
    }

    @Autowired
    CommonService commonService;
    @RequestMapping(path = "/menu", method = RequestMethod.POST)
    public Result getMenuList(HttpServletRequest request){
//        User user = (User)request.getAttribute("user");
        List<Map<String, Object>> menuList =
                commonService.getMenuList(1, 3);

        return Result.success(200, menuList);
    }

}

