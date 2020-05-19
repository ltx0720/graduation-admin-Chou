package com.example.service.controller.manager;

import com.example.service.pojo.ChangeTeacherApprove;
import com.example.service.pojo.News;
import com.example.service.pojo.Result;
import com.example.service.pojo.User;
import com.example.service.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author ltx
 * @Date 11:05 2020/5/19
 */
@RestController
@RequestMapping("/m_server")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(path = "/news",  method = RequestMethod.POST)
    public Result getNrws(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
//        User user1 = new User();
//        user1.setDepartment_id(1);
        List<News> newsList = managerService.getSimpleNewsManager(1);
        return Result.SUCCESS(200, newsList);
    }

    /**
     * 审批列表数据
     */
    @RequestMapping(path = "/ch_teacher_approve/{type}", method = RequestMethod.POST)
    public Result ch_teacher_approve(HttpServletRequest request, @PathVariable("type") String type){
        List<ChangeTeacherApprove> approveList = managerService.getChangeTeacherApprove(1, type);
        return Result.SUCCESS(200, approveList);
    }

    /**
     * 管理员审批, 批准/拒绝
     */
    @RequestMapping(path = "/approve/{action}", method = RequestMethod.POST)
    public Result approve(HttpServletRequest request, @PathVariable("action") String action){
//        User user = (User)request.getAttribute("user");
        User user = new User();
        user.setIdentify_id(1);
        int id = Integer.parseInt(request.getParameter("id"));

        boolean b = managerService.approveHandle(id, user, action);

        if (b){
            return Result.SUCCESS(200, "success");
        }

        return Result.ERROR(400, "fail");
    }
}
