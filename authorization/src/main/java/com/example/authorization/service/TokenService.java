package com.example.authorization.service;

import com.example.authorization.pojo.TokenDetail;
import org.springframework.stereotype.Service;

/**
 * @Author ltx
 * @Date 15:08 2020/4/14
 */
@Service
public interface TokenService {
    boolean writeToken(TokenDetail detail, String token);
}
