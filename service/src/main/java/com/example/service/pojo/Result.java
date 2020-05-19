package com.example.service.pojo;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.EscapedState;

/**
 * @Author ltx
 * @Date 21:10 2020/5/8
 *
 * 统一结果返回
 */
public class Result {
    public int code;
    public Object data;
    public String reason;

    private Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    private Result(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public static Result SUCCESS(int code, Object data){
        return new Result(code, data);
    }

    public static Result ERROR(int code, String reason){
        return new Result(code, reason);
    }
}
