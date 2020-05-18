package com.example.authorization.dao;

import com.example.authorization.pojo.TokenDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ltx
 * @Date 15:02 2020/4/14
 */
@Mapper
public interface TokenDao {
    @Insert("insert into token (issue, alive, body) values (#{detail.issue}, #{detail.alive}, #{token})")
    int writeToken(@Param("detail") TokenDetail detail, @Param("token") String token);
}
