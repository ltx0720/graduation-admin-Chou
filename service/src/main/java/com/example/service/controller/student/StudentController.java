package com.example.service.controller.student;

import com.example.service.annotation.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ltx
 * @Date 16:23 2020/5/8
 */
@RestController
@RequestMapping("/s_server")
@Role("stu")
public class StudentController {


}
