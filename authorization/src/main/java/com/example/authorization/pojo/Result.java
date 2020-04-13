package com.example.authorization.pojo;

import com.alibaba.fastjson.JSONObject;
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

    public static String jsonResult(int code, Object data){
        return JSONObject.toJSONString(new Result(code, data));
    }

    public static String jsonResult(int code, String reason){
        return JSONObject.toJSONString(new Result(code, reason));
    }

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
}
