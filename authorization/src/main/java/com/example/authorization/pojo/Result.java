package com.example.authorization.pojo;

import lombok.Data;

/**
 * @Author ltx
 * @Date 22:41 2020/4/12
 *
 * 统一结果对象
 */
@Data
public class Result {
    public int code;
    public Object data;
    public String reason;

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
        this.reason = "";
    }

    public Result(int code, String reason) {
        this.code = code;
        this.data = "";
        this.reason = reason;
    }


    public static Result login_error(){
        return new Result(Code.ERROR, Reason.UP_ERROR);
    }

    public static Result error(){
        return new Result(Code.ERROR, Reason.ERROR);
    }

    public static Result success(Object data){
        return new Result(Code.SUCCESS, data);
    }

    public static Result rsa_error(){
            return new Result(Code.SUCCESS, Reason.RSA_ERROR);
    }
    public static Result token_error(){
        return new Result(Code.SUCCESS, Reason.ERROR);
    }
}
