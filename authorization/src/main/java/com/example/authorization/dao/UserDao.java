package com.example.authorization.dao;

import com.example.authorization.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author ltx
 * @Date 22:46 2020/4/12
 */
@Mapper
public interface UserDao {

    @Select("select * from user where username=#{username} and password={password}")
    int findUser(User user);
}
