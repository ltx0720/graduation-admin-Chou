package com.example.authorization.exception.TokenException;

/**
 * @Author ltx
 * @Date 15:31 2020/4/14
 *
 * Token 处理时产生的异常，如写库失败
 */
public class TokenException extends Exception {
    public TokenException(String message) {
        super(message);
    }
}
