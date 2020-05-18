package com.example.authorization.service.impl;

import com.example.authorization.dao.UserDao;
import com.example.authorization.pojo.User;
import com.example.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author ltx
 * @Date 23:02 2020/4/12
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public User validate(String username, String password) {

        User user = dao.findUser(username, password);

        System.out.println(user);
        return user;
    }
}
