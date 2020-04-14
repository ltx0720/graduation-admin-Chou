package com.example.authorization.service.impl;

import com.example.authorization.dao.TokenDao;
import com.example.authorization.pojo.TokenDetail;
import com.example.authorization.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author ltx
 * @Date 15:08 2020/4/14
 */
@Component
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenDao dao;

    @Override
    public boolean writeToken(TokenDetail detail, String token) {
        int row = dao.writeToken(detail, token);

        return row == 1;
    }
}
