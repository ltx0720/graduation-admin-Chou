package com.example.fileserver.controller;

import com.example.fileserver.pojo.Result;
import com.example.fileserver.pojo.User;
import com.example.fileserver.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @Author ltx
 * @Date 23:56 2020/5/22
 *
 * 提供查询信息服务
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    /**
     * 查询当前用户可见的文件列表
     * 用于导师或管理员查询毕设文件
     */
    @RequestMapping(path = "/file-list", method = RequestMethod.POST)
    public Result getVisibleFileList(HttpServletRequest request){
//        User user = (User) request.getAttribute("user");
        User user = new User();
        user.setRole("SCHOOL");
        user.setIdentify_id(1);

        List list = getVisibleFileListHandler(user);

        return Result.success(200, list);
    }

    /**
     * 根据不同权限返回不同数据
     */
    private List getVisibleFileListHandler(User user){
        String role = user.getRole();
        int identify_id = user.getIdentify_id();

        if ("TEACHER".equals(role)) return queryService.getTeacherFileList(identify_id);

        if ("DEPARTMENT".equals(role))  return queryService.getDepartmentFileList(identify_id);

        if ("SCHOOL".equals(role)) return queryService.getSchoolFileList();


        return Collections.EMPTY_LIST;
    }

}
