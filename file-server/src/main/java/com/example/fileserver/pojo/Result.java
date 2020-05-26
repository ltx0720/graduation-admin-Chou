package com.example.fileserver.pojo;

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

    public static Result success(int code, Object data){
        return new Result(code, data);
    }

    public static Result success(Object data){
        return new Result(200, data);
    }

    public static Result error(int code, String reason){
        return new Result(code, reason);
    }
}
