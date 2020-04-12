package com.example.authorization.service.impl;

import com.example.authorization.dao.UserDao;
import com.example.authorization.pojo.User;
import com.example.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author ltx
 * @Date 23:02 2020/4/12
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public boolean validate(User user) {
        int result = dao.findUser(user);

        return result == 1;
    }
}
