package com.example.service.controller;

import com.example.service.dao.manager.ManagerDao;
import com.example.service.dao.student.StudentDao;
import com.example.service.pojo.ChangeTeacherApprove;
import com.example.service.pojo.Menu;
import com.example.service.pojo.Result;
import com.example.service.pojo.TeacherApprove;
import com.example.service.service.common.CommonService;
import com.example.service.service.manager.ManagerService;
import com.example.service.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        System.out.println("upload file");
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

        return Result.SUCCESS(200,  managerService.getMenuList(1));
    }


}

