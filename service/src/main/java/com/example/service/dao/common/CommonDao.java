package com.example.service.dao.common;

import com.example.service.pojo.Menu;
import com.example.service.pojo.News;
import com.example.service.pojo.User;
import com.google.gson.annotations.SerializedName;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author ltx
 * @Date 16:34 2020/5/8
 *
 * 公共 dao
 */
@Mapper
public interface CommonDao {

    /**
     * 全部消息通知(simple)
     */
    @Select("  select n.author as author, n.create_time as create_time, n.id as id " +
            " from news n left join student s" +
            " on s.school_id = 1 and s.id=#{student_id} " +
            " where n.school_id=#{school_id} " +
            " or n.state=0 " +
            " or (n.department_id=s.department_id and n.state=1) " +
            " or (n.department_id=s.department_id and n.teacher_id=s.teacher_id and n.state=2)")
    List<News> getSimpleNews(@Param("school_id") int school_id, @Param("student_id") int student_id);

    /**
     * 全部消息通知(simple)
     */
    @Select("select news.create_time, news.author, news.title from news where state=0 " +
            " or (department_id=#{department_id} and state = 1) " +
            " or (department_id=#{department_id} and teacher_id=#{teacher_id} and state = 2)")
    List<News> getSimpleNewsTeacher(@Param("department_id") int department_id, @Param("teacher_id") int teacher_id);


    /**
     * 校级消息通知
     */
    @Select("select n.author as author, n.create_time as create_time, n.id as id " +
            "from news n where n.school_id=#{school_id} and n.state=0")
    List<News> getSchoolSimpleNews(@Param("school_id") int school_id);

    /**
     * 院级消息通知
     */
    @Select("select n.author as author, n.create_time as create_time, n.id as id " +
            "from news n where n.school_id=#{school_id} and n.department_id=#{department_id} and n.state=1")
    List<News> getDepartmentSimpleNews(@Param("school_id") int school_id, @Param("department_id") int department_id);

    /**
     * 获取通知的详细信息
     */
    @Select("select news.create_time as create_time, news.author as author, news_detail.content as content from news left join news_detail " +
            "on news.id = news_detail.news_id " +
            "where news.id = #{id}")
    News getNewsDetail(int id);

    /**
     * 获取通知的内容
     */
//
    @Select("select content from news where id=#{id} and (department_id=#{department_id} or department_id=0 )")
    String getNewsContent(@Param("department_id") int department_id, @Param("id") int id);



    @Select("insert into news (department_id, create_time, state, teacher_id, author, content) " +
            "values (#{user.department_id}, #{news.create_time}, 0, #{user.identify_id}, #{news.author}, #{news.content})")
    Integer teacherPublishNews(@Param("user") User user, @Param("news") News news);

    /**
     * 获取用户对应的权限路由
     */
    @Select("select id, path, component, icon, father_id, title from menu where department_id=#{department_id} and roles=#{roles} and active=1")
    @Results(id = "stuList", value = {
            @Result(property = "meta.title", column = "title"),
            @Result(property = "meta.icon", column = "icon"),
    })
    List<Menu> getMenuList(@Param("department_id") int department_id, @Param("roles") int roles);

}

