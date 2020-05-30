package com.example.service.controller.manager;

import com.example.service.pojo.*;
import com.example.service.service.manager.ManagerService;
import com.example.service.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author ltx
 * @Date 11:05 2020/5/19
 */
@RestController
@RequestMapping("/m_server")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 获取消息通知
     */
    @RequestMapping(path = "/news",  method = RequestMethod.POST)
    public Result getNrws(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        List<News> newsList = managerService.getSimpleNewsManager(user.getDepartment_id());
        return Result.success(200, newsList);
    }

    /**
     * 获取审批列表数据
     */
    @RequestMapping(path = "/approve/type/{type}", method = RequestMethod.POST)
    public Result ch_teacher_approve(HttpServletRequest request, @PathVariable("type") String type){
        List<ChangeTeacherApprove> approveList = managerService.getChangeTeacherApprove(1, type);
        return Result.success(200, approveList);
    }

    /**
     * 管理员审批, 批准/拒绝
     */
    @RequestMapping(path = "/approve/action/{action}", method = RequestMethod.POST)
    public Result approve(HttpServletRequest request, @PathVariable("action") String action){
        String opinion = request.getParameter("opinion");
        User user = (User)request.getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));

        boolean b = managerService.approveHandle(id, user, opinion, action);

        if (b){
            return Result.success(200, "success");
        }

        return Result.error(400, "fail");
    }

    /**
     * 更新功能菜单状态
     */
    @RequestMapping(path = "/update_menu", method = RequestMethod.POST)
    public Result updateMenu(HttpServletRequest request){
        String json = request.getParameter("json");
        Object map = GsonUtil.fromJson(json, Map.class);

//        System.out.println(map);
        return null;
    }


    /**
     * 发布消息通知
     */
    @RequestMapping(path = "/publish", method = RequestMethod.POST)
    public Result mpublish(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        String content = request.getParameter("content");
        String title = request.getParameter("title");

        News news = new News();
        news.setContent(content);
        news.setTitle(title);
        news.setCreate_time(new Date().toString());

        boolean res = managerService.managerPublish(user, news);

        return Result.success(200, res);
    }


    /**
     * 发布导师信息，供学生选择
     */
    @RequestMapping(path = "/submit_teacher", method = RequestMethod.POST)
    public Result submitTeacher(HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        String data = request.getParameter("teacher");
        SelectTeacher teacher = GsonUtil.fromJson(data, SelectTeacher.class);

        boolean res = managerService.submitTeacher(user.getIdentify_id(), teacher);

        return Result.success(200, res);
    }

    /**
     * 查看导师供学生选择的情况
     */
    @RequestMapping(path = "/teacher", method = RequestMethod.POST)
    public Result getS(HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        List<SelectTeacher> list = managerService.getTeacherList(user.getIdentify_id());

        return Result.success(200, list);
    }

    /**
     * 查看已未发布的导师
     */
    @RequestMapping(path = "/not_publish_teacher", method = RequestMethod.POST)
    public Result getNotPublishTeacherList(HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        List<SelectTeacher> list = managerService.getCanPublishTeacherList(user.getIdentify_id());

        return Result.success(200, list);
    }

    /**
     * 改变供选择的导师的状态，用来控制学生端的可见
     */
    @RequestMapping(path = "/change/state", method = RequestMethod.POST)
    public Result changeSelectTeacherState(HttpServletRequest request){
        User user = (User)request.getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));
        int state = Integer.parseInt(request.getParameter("state"));

        boolean res = managerService.changeSelectTeacherState(user.getIdentify_id(), id, state);

        return Result.success(200, res);
    }


}
