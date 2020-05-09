package com.example.service.exception;

/**
 * @Author ltx
 * @Date 20:56 2020/5/9
 *
 * token 异常
 */
public class TokenErrorException extends Exception {
    public TokenErrorException(String message) {
        super(message);
    }
}
