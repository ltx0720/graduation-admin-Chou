package com.example.service.dao.common;

import com.example.service.pojo.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author ltx
 * @Date 16:34 2020/5/8
 */
@Mapper
public interface CommonDao {

    @Select("select n.author as author, n.create_time as create_time, n.id as id from news n " +
            "left join student s on s.school_id = #{school_id} and s.student_id=#{student_id} " +
            "where n.school_id=1 " +
            "and n.user_id = u.id " +
            "or state=0 " +
            "or (n.department_id=s.department_id and state=1) " +
            "or (n.department_id=s.department_id and n.teacher_id=s.teacher_id and state=2)")
    List<News> getSimpleNews(@Param("school_id") int school_id, @Param("student_id") int student_id);


    @Select("select news.create_time as create_time, news.author as author, news_detail.content as content from news left join news_detail " +
            "on news.id = news_detail.news_id " +
            "where news.id = #{id}")
    News getNewsDetail(int id);
}
