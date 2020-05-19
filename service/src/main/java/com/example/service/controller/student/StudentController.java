package com.example.service.controller.student;

import com.example.service.pojo.*;
import com.example.service.service.common.CommonService;
import com.example.service.service.student.StudentService;
import com.example.service.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author ltx
 * @Date 16:23 2020/5/8
 */
@RestController
@RequestMapping("/s_server")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CommonService commonService;

    /**
     * 获取消息通知
     */
    @RequestMapping(path = "/news", method = RequestMethod.POST)
    public Result getNews(HttpServletRequest request){
        List<News> newsList = studentService.getSimpleNews(1);
        return Result.SUCCESS(200, newsList);
    }

    /**
     * 获取可供选择的导师列表
     */
    @RequestMapping(path = "/select_teacher", method = RequestMethod.POST)
    public Result getSelectTeacher(HttpServletRequest request){
        User user1 = new User((byte) 0);
        user1.setSchool_id(1);
        user1.setDepartment_id(1);

        List<SelectTeacher> selectTeacher = studentService.getSelectTeacher(user1.getSchool_id(), user1.getDepartment_id());
        return Result.SUCCESS(200, selectTeacher);
    }

    /**
     * 查询是否已选择导师
     */
    @RequestMapping(path = "/isselected_teacher", method = RequestMethod.POST)
    public Result isSelectedTeacher(HttpServletRequest request){
        boolean isSelected = studentService.isSelectedTeacher(1);
        return Result.SUCCESS(200, isSelected);
    }

    /**
     * 选择导师
     */
    @RequestMapping(path = "/select", method = RequestMethod.POST)
    public Result select(HttpServletRequest request){
        int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
        boolean result = studentService.selectTeacher(1, teacher_id);
        return Result.SUCCESS(200, result);
    }

    /**
     * 是否已选择课题
     */
    @RequestMapping(path = "/isselected_topic", method = RequestMethod.POST)
    public Result isSelectTopic(HttpServletRequest request){
        boolean result = studentService.isSelectedTopic(1);

        return Result.SUCCESS(200, result);
    }

    /**
     * 选择导师
     */
    @RequestMapping(path = "/topic", method = RequestMethod.POST)
    public Result topicList(HttpServletRequest request){
        List<Topic> topicList = studentService.getSelectTopicList(1);

        return Result.SUCCESS(200, topicList);
    }

    /**
     *
     */
    @RequestMapping(path = "/message", method = RequestMethod.POST)
    public String getMsg(HttpServletRequest request){
        User user = (User) request.getAttribute("user");

//        List<News> news = commonService.getSimpleNews(user.getSchool_id(), user.getDepartment_id());

        return GsonUtil.toJson("");
    }

}
