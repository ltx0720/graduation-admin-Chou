package com.example.fileserver.controller;

import com.example.fileserver.pojo.User;
import com.example.fileserver.pojo.file.File;
import com.example.fileserver.pojo.file.GroupFile;
import com.example.fileserver.pojo.Result;
import com.example.fileserver.service.UploadService;
import com.example.fileserver.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ltx
 * @Date 17:39 2020/5/22
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    private static String bucket;
    private static String publicKey;
    private static String privateKey;
    @Autowired
    private UploadService uploadService;

    /**
     * 请求公钥
     */
    @RequestMapping(path = "/publickey", method = RequestMethod.GET)
    public Result getPublickey(){
        return Result.success(Base64.encode(publicKey.getBytes()));
    }

    /**
     * 获取 token
     */
    @RequestMapping(path = "/token", method = RequestMethod.POST)
    public Result getToken(HttpServletRequest request) throws Exception {
        String ciphertext = request.getParameter("key");

        // rsa 私钥解密
        byte[] bytes = RSAUtils.privateDecrypt(Base64.decode(ciphertext), RSAUtils.string2PrivateKey(privateKey));
        String key = new String(bytes);

        String token = QiniuUtil.getUploadToken();

        // aes 加密
        String encrypt = AESUtil.encrypt(token, key);

        return Result.success(Base64.encode(encrypt.getBytes()));
    }

    /**
     * 把上传记录写库
     */
    @RequestMapping(path = "/record", method = RequestMethod.POST)
    public Result writeUploadLog(HttpServletRequest request){
        User user = (User) request.getAttribute("user");

        String key = request.getParameter("key");
        int type_id = Integer.parseInt(request.getParameter("type_id"));

        GroupFile file = new GroupFile();
        file.setBucket(bucket);
        file.setFile_name(user.getAuthorities() + ("-"+user.getIdentify_id()+"-")+ key);
        file.setType_id(type_id);
        file.setUrl(String.format("%s%s", bucket, key));

        boolean result = uploadService.witeUploadRecord(1, file);
        return Result.success(200, result);
    }


    @Value("${rsa.publickey}")
    public void setPublicKey(String publicKey) {
        UploadController.publicKey = publicKey;
    }


    @Value("${rsa.privatekey}")
    public void setPrivateKey(String privatekey) {
        UploadController.privateKey = privatekey;
    }

    @Value("${qiniu.domainOfBucket}")
    public void setBucket(String bucket) {
        UploadController.bucket = bucket;
    }
}
