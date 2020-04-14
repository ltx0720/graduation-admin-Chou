package com.example.authorization.exception.LoginException;

/**
 * @Author ltx
 * @Date 17:00 2020/4/14
 *
 * 签名错误或是用户名密码错误
 */
public class LoginException extends Exception {
    public LoginException(String message) {
        super(message);
    }
}
