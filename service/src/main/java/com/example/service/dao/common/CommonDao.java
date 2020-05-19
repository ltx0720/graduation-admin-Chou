package com.example.service.dao.common;

import com.example.service.pojo.Menu;
import com.example.service.pojo.News;
import com.example.service.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author ltx
 * @Date 16:34 2020/5/8
 * <p>
 * 公共 dao
 */
@Mapper
public interface CommonDao {

    /**
     * 获取用户对应的权限路由
     */
    @Select("select * from menu where manager_id=#{manager_id} and roles=#{roles}")
    List<Menu> getMenuList(@Param("manager_id") int manager_id, @Param("roles") int roles);

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
    @Select("select news.create_time, news.author, news.title from news where school_id=#{school_id} " +
            " or state=0 " +
            " or (department_id=#{department_id} and state = 1) " +
            " or (department_id=#{department_id} and teacher_id=#{teacher_id} and state = 2)")
    List<News> getSimpleNewsTeacher(@Param("school_id") int school_id, @Param("department_id") int department_id, @Param("teacher_id") int teacher_id);


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

    @Select("insert into news (school_id, department_id, create_time, state, teacher_id, author, content) " +
            "values (#{user.school_id}, #{user.department_id}, #{news.create_time}, 1, #{user.identify_id}, #{news.author}, #{news.content})")
    Integer teacherPublishNews(@Param("user") User user, @Param("news") News news);

}

