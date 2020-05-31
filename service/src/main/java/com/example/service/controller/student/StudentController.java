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
        return Result.success(200, newsList);
    }

    /**
     * 获取可供选择的导师列表
     */
    @RequestMapping(path = "/teacher_select", method = RequestMethod.POST)
    public Result getSelectTeacher(HttpServletRequest request){
        User user = (User)request.getAttribute("user");

        List<SelectTeacher> selectTeacher = studentService.getSelectTeacher(user.getDepartment_id());
        return Result.success(200, selectTeacher);
    }

    /**
     * 查询是否已选择导师
     */
    @RequestMapping(path = "/is_selected_teacher", method = RequestMethod.POST)
    public Result isSelectedTeacher(HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        boolean isSelected = studentService.isSelectedTeacher(user.getIdentify_id());
        return Result.success(200, isSelected);
    }


    @RequestMapping(path = "/select_teacher", method = RequestMethod.POST)
    public Result selectTeacher(HttpServletRequest request){
        System.out.println("select");
        int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
        User user = (User)request.getAttribute("user");
        boolean resutl = studentService.selectTeacher(user.getIdentify_id(), teacher_id);

        return Result.success(200, resutl);
    }

    /**
     * 课题列表
     */
    @RequestMapping(path = "/topic", method = RequestMethod.POST)
    public Result topicList(HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        List<Topic> topicList = studentService.getSelectTopicList(user.getIdentify_id());

        return Result.success(200, topicList);
    }

    /**
     * 是否已选择课题
     */
    @RequestMapping(path = "/is_selected_topic", method = RequestMethod.POST)
    public Result isSelectTopic(HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        boolean result = studentService.isSelectedTopic(user.getIdentify_id());

        return Result.success(200, result);
    }

    /**
     * 选择课题
     */
    @RequestMapping(path = "/select_topic", method = RequestMethod.POST)
    public Result select(HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        int topic_id = Integer.parseInt(request.getParameter("topic_id"));
        boolean res = studentService.selectTopic(user.getIdentify_id(), topic_id);

        return Result.success(200, res);
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
