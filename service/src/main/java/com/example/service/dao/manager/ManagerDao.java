package com.example.service.dao.manager;

import com.example.service.pojo.ChangeTeacherApprove;
import com.example.service.pojo.News;
import com.example.service.pojo.TeacherApprove;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author ltx
 * @Date 22:48 2020/5/11
 *
 * 管理员 Dao 层
 */
@Mapper
public interface ManagerDao {

    /**
     * 获取消息通知
     */
    @Select("select news.create_time, news.author, news.title from news " +
            "where state=0 " +
            " or (state=1 and department_id=#{department_id}) ")
    List<News> getSimpleNewsManager(@Param("department_id") int department_id);

    /**
     * 待审批条目
     */
    @Select("select s.name, s.class_name, act.teacher1_name, act.teacher2_name, at.name, act.opinion " +
            "from approve_change_teacher act left join student s " +
            "on act.student_id = s.id " +
            "left join approve_type at on act.approvetype_id = at.id " +
            "where act.manager_id=#{manager_id} and act.state=0")
    @Results(id = "changeTeacherApprove", value = {
            @Result(property = "student_name", column = "s.name"),
            @Result(property = "approve_type_name", column = "at.name"),
            @Result(property = "old_teacher_name", column = "teacher1_name"),
            @Result(property = "new_teacher_name", column = "teacher2_name"),
    })
    List<ChangeTeacherApprove> getTeacherApprovePending(int manager_id);

    /**
     * 已审批条目
     */
    @Select("select s.name, s.class_name, act.teacher1_name, act.teacher2_name, at.name, act.opinion " +
            "from approve_change_teacher act left join student s " +
            "on act.student_id = s.id " +
            "left join approve_type at on act.approvetype_id = at.id " +
            "where act.manager_id=#{manager_id} and act.state!=0")

    @ResultMap(value = "changeTeacherApprove")
    List<ChangeTeacherApprove> getTeacherApproveFinished(int manager_id);

    /**
     * 审批条目
     */
    @Update("update approve_change_teacher set state=#{state} where id=#{id} and manager_id=#{manager_id}")
    Integer approveHandle(@Param("id") int id, @Param("manager_id") int manager_id, @Param("state") int state);

}
