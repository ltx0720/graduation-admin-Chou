package com.example.service.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @Author ltx
 * @Date 20:05 2020/5/9
 *
 * Gson 工具类
 */
public class GsonUtil {

    private static Gson filterNullGson;
    private static Gson nullableGson;

    static {
        nullableGson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
                .create();
        filterNullGson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
                .create();
    }

    protected GsonUtil() {}

    /**
     * 根据对象返回json   不过滤空值字段
     */
    public static String toJsonWtihNullField(Object object){
        return nullableGson.toJson(object);
    }

    /**
     * 根据对象返回json  过滤空值字段
     */
    public static String toJson(Object obj){
        return filterNullGson.toJson(obj);
    }

    /**
     * 将json转化为对应的实体对象
     * new TypeToken<HashMap<String, Object>>(){}.getType()
     */
    public static <T> T fromJson(byte[] json, Type type){
        return nullableGson.fromJson(json, type);
    }

}
