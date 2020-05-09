package com.example.service.pojo;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.EscapedState;

/**
 * @Author ltx
 * @Date 21:10 2020/5/8
 *
 * 统一结果返回
 */
public class Result<T> {
    private int code;
    private T data;
    private String reason;

    private Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private Result(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public static <T> Result<T> successResult(int code, T data){
        return new Result<>(code, data);
    }

    public static Result errorResult(int code, String reason){
        return new Result<>(code, reason);
    }
}
