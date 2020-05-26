package com.example.service.controller.common;

import com.example.service.pojo.News;
import com.example.service.pojo.Result;
import com.example.service.pojo.User;
import com.example.service.service.common.CommonService;
import com.example.service.service.student.StudentService;
import com.example.service.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author ltx
 * @Date 16:27 2020/5/8
 */
@RestController
@RequestMapping("/c_server")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private StudentService studentService;

    /**
     * 学生获取消息通知
     */
    @RequestMapping(value = "/s_news", method = RequestMethod.POST)
    public Result getMsg(HttpServletRequest request){
        System.out.println("news");
//        User user = (User) request.getAttribute("user");
        User user1 = new User((byte) 0);
        user1.setSchool_id(1);
        user1.setDepartment_id(1);

        List<News> news = commonService.getSimpleNews(user1.getSchool_id(), user1.getDepartment_id());

        return Result.success(200, news);
    }

    /**
     * 导师获取消息通知
     */
    @RequestMapping(value = "/t_news", method = RequestMethod.POST)
    public Result getMsgTeacher(HttpServletRequest request){
//        User user = (User) request.getAttribute("user");
        User user1 = new User((byte) 0);
        user1.setSchool_id(1);
        user1.setDepartment_id(1);

        List<News> news = commonService.getSimpleNewsTeacher(user1);

        return Result.success(200, news);
    }

    /**
     * 获取通知的详细信息
     */
    @RequestMapping("/news/{news_id}")
    public Result message(@PathVariable("news_id") int news_id){
        System.out.println("news_id");
        return Result.success(200, commonService.getNewsDetail(news_id));
    }


    /**
     * 导师推送通知
     */
    @RequestMapping(value = "/t_publish", method = RequestMethod.POST)
    public Result publish(HttpServletRequest request){
        String content = request.getParameter("content");

        User user = new User((byte) 1);
        user.setSchool_id(1);
        user.setDepartment_id(1);
        user.setIdentify_id(1);

        News news = new News();
        news.setAuthor("导师1");
        news.setContent(content);
        news.setCreate_time(new Date());

        commonService.teacherPublishNews(user, news);

        return Result.success(200, "");
    }


    @RequestMapping(value = "/m_publish", method = RequestMethod.POST)
    public Result mpublish(HttpServletRequest request){


        String content = request.getParameter("content");

        System.out.println(content);


        User user = new User((byte) 1);
        user.setSchool_id(1);
        user.setDepartment_id(1);
        user.setIdentify_id(1);

        News news = new News();
        news.setAuthor("导师1");
        news.setContent(content);
        news.setCreate_time(new Date());

        commonService.teacherPublishNews(user, news);

        return Result.success(200, "");
    }
}
