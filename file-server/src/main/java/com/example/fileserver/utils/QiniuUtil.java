package com.example.fileserver.utils;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author ltx
 * @Date 18:41 2020/5/22
 *
 * 七牛工具类
 */
@Component
public class QiniuUtil {
    private static String accessKey;
    private static String secretKey;
    private static String bucket;

    /**
     * 生成文件上传的 token
     */
    public static String getUploadToken(){

        String accessKey1 = "RCl9E9RrR0-kScr39nZySWLhF8drIxWJQ4qUeq58";
        String secretKey1 = "A6Dr6ZuGxwZ644HVholBmvK29ZOuGZ4Se8ADurvU";
        String bucket1 = "graduation-admin-fileserver";


        Auth auth = Auth.create(accessKey1, secretKey1);
        String upToken = auth.uploadToken(bucket1);

        return upToken;
    }

    public static String getDownLoadUrl(){
        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(bucket);
        return uploadToken;
    }


    @Value("${qiniu.accessKey}")
    public void setAccessKey(String accessKey) {
        QiniuUtil.accessKey = accessKey;
    }

    @Value("${qiniu.secretKey}")
    public void setSecretKey(String secretKey) {
        QiniuUtil.secretKey = secretKey;
    }

    @Value("${qiniu.bucket}}")
    public void setBucket(String bucket) {
        QiniuUtil.bucket = bucket;
    }
}
