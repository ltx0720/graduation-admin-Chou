package com.example.service.dao.student;

import com.example.service.pojo.Menu;
import com.example.service.pojo.News;
import com.example.service.pojo.SelectTeacher;
import com.example.service.pojo.Topic;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

/**
 * @Author ltx
 * @Date 15:47 2020/5/11
 */
@Mapper
public interface StudentDao {
    /**
     * 全部消息通知(simple)
     */
    @Select(" select n.title, n.author, n.create_time, n.id " +
            " from news n left join student s" +
            " on s.id=#{student_id} " +
            " where n.state=0 " +
            " or (n.department_id=s.department_id and n.state=1) " +
            " or (n.department_id=s.department_id and n.teacher_id=s.teacher_id and n.state=2)")
    List<News> getSimpleNews(int student_id);

    /**
     * 可供选择的导师
     */
    @Select("select t.information, t.name as teacher_name, t.major, st.num from select_teacher st right join teacher t " +
            "on st.teacher_id = t.id " +
            "where st.school_id=#{school_id} and st.department_id=#{department_id} ")
    List<SelectTeacher> getSelectTeacher(@Param("school_id") int school_id, @Param("department_id")int department_id);

    /**
     * 获取已选择的导师
     */
    @Select("select teacher_id from student where id=#{student_id}")
    String getSelectedTeacher(int student_id);

    /**
     * 选择导师
     */
    @Select("update student set teacher_id=#{teacher_id} " +
            "where department_id#{department_id} and student_id=#{student_id}")
    Integer selectTeacher(@Param("department_id") int department_id, @Param("student_id") int student_id, @Param("teacher_id") int teacher_id);


    /**
     * 可供选择的课题列表
     */
    @Select("select t.id, t.title, t.direction, t.state from topic t " +
            "left join student s on t.teacher_id = s.teacher_id " +
            "where s.id=#{student_id}")
    List<Topic> getSelectTopicList(int student_id);

    /**
     * 获取已选择的课题
     */
    @Select("select topic_id from student where id=#{student_id}")
    String getSelectedTopic(int student_id);

    /**
     * 获取学生可见的所有功能菜单
     */
    @Select("select m.id as id, m.father_id as father_id, m.roles as roles, m.title as title, m.path as path, m.icon as icon " +
            "from menu m where roles=#{roles}")
    @Results(id = "stuList", value = {
            @Result(property = "meta.title", column = "title"),
            @Result(property = "meta.icon", column = "icon"),
    })
    List<Menu> getStudentMenu(int roles);

    /**
     * 选择导师
     */
    @Select("call student_selected_teacher(" +
            "#{param.student_id, mode=IN, jdbcType=INTEGER}, " +
            "#{param.teacher_id, mode=IN, jdbcType=INTEGER}, " +
            "#{param.result, mode=OUT, jdbcType=BOOLEAN})"
    )
    @Options(statementType= StatementType.CALLABLE)
    void seletTeacher(@Param("param") Map param);

    /**
     * 选择课题
     */
    @Select("call student_select_topic(" +
            "#{param.student_id, mode=IN, jdbcType=INTEGER}, " +
            "#{param.topic_id, mode=IN, jdbcType=INTEGER}, " +
            "#{param.result, mode=OUT, jdbcType=BOOLEAN})"
    )
    @Options(statementType = StatementType.CALLABLE)
    void seletTopic(@Param("param") Map param);
}
