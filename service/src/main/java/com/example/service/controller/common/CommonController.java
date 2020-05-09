package com.example.service.controller.common;

import com.example.service.annotation.Role;
import com.example.service.pojo.News;
import com.example.service.pojo.Result;
import com.example.service.service.common.CommonService;
import com.example.service.utils.TokenUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author ltx
 * @Date 16:27 2020/5/8
 */
@RestController
@RequestMapping("/c_server")
@Role("com")
public class CommonController {

    private CommonService commonService;

    /**
     * 获取通知消息简单信息
     */
    @RequestMapping("/news")
    public Result<List<News>> message(HttpServletRequest request){
        int studentID = TokenUtil.getStudentID(request);
        int schoolID = TokenUtil.getSchoolID(request);

        return Result.successResult(200, commonService.getSimpleNews(schoolID, studentID));
    }

    /**
     * 获取通知的详细信息
     */
    @RequestMapping("/news/{news_id}")
    public Result<News> message(@PathVariable("news_id") int news_id){
        System.out.println("news_id");
        return Result.successResult(200, commonService.getNewsDetail(news_id));
    }
}
