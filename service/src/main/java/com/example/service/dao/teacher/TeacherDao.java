package com.example.service.dao.teacher;

import com.example.service.pojo.Student;
import com.example.service.pojo.TeacherApprove;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author ltx
 * @Date 17:22 2020/5/11
 */
@Mapper
public interface TeacherDao {

    /**
     * 获取导师的学生信息
     */
    @Select("select s.class_name, s.name, s.topic_title " +
            "from student s " +
            "where department_id=#{department_id} and teacher_id=#{teacher_id}")
    List<Student> getAllStudent(@Param("department_id") int department_id, @Param("teacher_id") long teacher_id);

    /**
     * 待审批条目
     */
    @Select("select * from approve_topic where teacher_id=#{teacher_id} and state=0")
    List<TeacherApprove> getTeacherApprovePending(int teacher_id);

    /**
     * 已审批条目
     */
    @Select("select * from approve_topic where teacher_id=#{teacher_id} and state!=0")
    List<TeacherApprove> getTeacherApproveFinished(int teacher_id);

    /**
     * 审批条目
     */
    @Update("update approve_topic set state=#{state}, opinion=#{opinion} where id=#{id} and teacher_id=#{teacher_id}")
    Integer approveHandle(@Param("id") int id, @Param("opinion") String opinion, @Param("teacher_id") int teacher_id, @Param("state") int state);
}
