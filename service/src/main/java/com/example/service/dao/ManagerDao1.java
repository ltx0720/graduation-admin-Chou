package com.example.service.dao;

import com.example.service.pojo.MenuState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Author ltx
 * @Date 12:56 2020/5/21
 */
@Repository
public interface ManagerDao1 {
    void test(@Param("map") Map<Integer, MenuState> map);
}
