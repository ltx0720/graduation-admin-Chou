package com.example.fileserver.handler;

import com.example.fileserver.pojo.Result;
import com.example.fileserver.utils.GsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ltx
 * @Date 16:39 2020/5/23
 */
@Component
public class AuthenticationFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.getWriter().write(GsonUtil.toJson(Result.error(404, "fail")));
    }
}
