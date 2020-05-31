package com.example.service.controller.teacher;

import com.example.service.pojo.Result;
import com.example.service.pojo.Student;
import com.example.service.pojo.TeacherApprove;
import com.example.service.pojo.User;
import com.example.service.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author ltx
 * @Date 21:24 2020/5/15
 */
@RestController
@RequestMapping("t_server")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(path = "/student", method = RequestMethod.POST)
    public Result getStudent(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        List<Student> studentList = teacherService.getAllStudent(user);

        return Result.success(200, studentList);
    }

    /**
     * 导师获取待审批条目
     */
    @RequestMapping(path = "/topic_approve/{type}", method = RequestMethod.POST)
    public Result getTopicApprove(HttpServletRequest request, @PathVariable("type") String type){
        List<TeacherApprove> teacherApprove = teacherService.getTeacherApprove(1, type);
        return Result.success(200, teacherApprove);
    }

    /**
     * 导师审批申请，批准/拒绝
     */
    @RequestMapping(path = "/approve/{action}", method = RequestMethod.POST)
    public Result approve(HttpServletRequest request, @PathVariable("action") String action){
        User user = (User)request.getAttribute("user");

        Integer id = Integer.valueOf(request.getParameter("id"));
        String opinion = request.getParameter("opinion");

        boolean b = teacherService.approveHandle(id, opinion, user, action);

        return Result.success(200, b);
    }
}
