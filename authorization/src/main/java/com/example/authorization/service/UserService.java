package com.example.authorization.service;

import com.example.authorization.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @Author ltx
 * @Date 22:59 2020/4/12
 */
@Service
public interface UserService {

    User validate(String username, String password);

//    void validate(User user);
}
