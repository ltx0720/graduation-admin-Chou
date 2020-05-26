package com.example.fileserver.controller.Test;//package com.example.fileserver.controller;

import com.example.fileserver.utils.QiniuUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;


/**
 * @Author ltx
 * @Date 21:08 2020/5/21
 */
public class TEST {

    public static void tes() throws QiniuException {
        Configuration cfg = new Configuration(Region.huadong());
        UploadManager uploadManager = new UploadManager(cfg);

        String accessKey = "RCl9E9RrR0-kScr39nZySWLhF8drIxWJQ4qUeq58";
        String secretKey = "A6Dr6ZuGxwZ644HVholBmvK29ZOuGZ4Se8ADurvU";
        String bucket = "graduation-admin-fileserver";

        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\LTX\\Desktop\\bsicons\\timg.jpg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        String upToken1 = QiniuUtil.getUploadToken();
        System.out.println(upToken);
        System.out.println(upToken1);

        Response response = uploadManager.put(localFilePath, key, upToken1);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//        System.out.println(putRet.key);
//        System.out.println(putRet.hash);
    }

    public static void main(String[] args) throws QiniuException {
        tes();
    }
}
