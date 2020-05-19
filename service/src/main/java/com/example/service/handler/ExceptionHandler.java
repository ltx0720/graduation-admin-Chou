package com.example.service.handler;

import com.example.service.exception.TokenErrorException;
import com.example.service.pojo.Result;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author ltx
 * @Date 16:28 2020/4/14
 *
 * 全局统一异常处理
 */
@RestControllerAdvice
public class ExceptionHandler {
    /**
     * token 错误
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = TokenErrorException.class)
    public Result tokenError(Exception e){
        return Result.ERROR(400, e.getMessage());
    }
}
