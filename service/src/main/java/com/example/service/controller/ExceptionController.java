package com.example.service.controller;

import com.example.service.exception.TokenErrorException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ltx
 * @Date 21:12 2020/5/9
 *
 * 处理 Filter 中的异常
 */
@RestController
@RequestMapping("/filter")
public class ExceptionController {

    @RequestMapping("/token_exception")
    public void ex() throws TokenErrorException {
        throw new TokenErrorException("token 错误");
    }
}
