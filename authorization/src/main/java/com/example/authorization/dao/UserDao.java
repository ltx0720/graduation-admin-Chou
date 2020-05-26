package com.example.authorization.dao;

import com.example.authorization.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.Map;

/**
 * @Author ltx
 * @Date 22:46 2020/4/12
 */
@Mapper
public interface UserDao {

    @Select("select department_id, name, roles, identify_id from user " +
            "where username = #{username} and password = MD5(concat(left(#{password}, 5), #{password}, right(#{username}, 2)))")
    @Options(statementType= StatementType.CALLABLE)
    User findUser(@Param("username") String username, @Param("password") String password);
}
