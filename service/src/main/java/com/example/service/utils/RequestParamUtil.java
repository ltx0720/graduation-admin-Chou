package com.example.service.utils;

import com.example.service.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ltx
 * @Date 22:35 2020/5/12
 */
public class RequestParamUtil {

    public static int getSchoolId(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        return user.getSchool_id();
    }
}
