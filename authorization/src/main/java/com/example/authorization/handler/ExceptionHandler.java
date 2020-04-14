package com.example.authorization.handler;

import com.example.authorization.exception.LoginException.LoginException;
import com.example.authorization.exception.RSAException.RSAException;
import com.example.authorization.exception.TokenException.TokenException;
import com.example.authorization.pojo.Result;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author ltx
 * @Date 16:28 2020/4/14
 */
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = RSAException.class)
    public Result rsa(Exception e){
        return Result.rsa_error();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = TokenException.class)
    public Result token(Exception e){
       return Result.token_error();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = LoginException.class)
    public Result login(HttpServletResponse response, Exception e){
        return Result.login_error();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Result exp(HttpServletResponse response, Exception e){
        return Result.error();
    }
}
