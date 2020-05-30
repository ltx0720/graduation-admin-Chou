package com.example.service.dao.manager;

import com.example.service.pojo.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Result;

import java.util.List;
import java.util.Map;

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
    @Select("select create_time as create_time, author as author, title as title from news " +
            "where state=0 " +
            " or (state=1 and department_id=#{department_id}) ")
    List<News> getSimpleNewsManager(int department_id);

    /**
     * 待审批条目
     */
    @Select("select act.id, s.name as student_name, s.class_name, act.teacher1_name, act.teacher2_name, at.name as type_name, act.opinion " +
            "from approve_change_teacher act left join student s " +
            "on act.student_id = s.id " +
            "left join approve_type at on act.approvetype_id = at.id " +
            "where act.manager_id=#{manager_id} and act.state=0")
    @Results(id = "changeTeacherApprove", value = {
            @Result(property = "student_name", column = "student_name"),
            @Result(property = "approve_type_name", column = "type_name"),
            @Result(property = "old_teacher_name", column = "teacher1_name"),
            @Result(property = "new_teacher_name", column = "teacher2_name"),
    })
    List<ChangeTeacherApprove> getTeacherApprovePending(int manager_id);

    /**
     * 已审批条目
     */
    @Select("select act.id, s.name as student_name, s.class_name, act.teacher1_name, act.teacher2_name, at.name as type_name, act.opinion " +
            "from approve_change_teacher act left join student s " +
            "on act.student_id = s.id " +
            "left join approve_type at on act.approvetype_id = at.id " +
            "where act.manager_id=#{manager_id} and act.state!=0")

    @ResultMap(value = "changeTeacherApprove")
    List<ChangeTeacherApprove> getTeacherApproveFinished(int manager_id);

    /**
     * 审批条目
     */
    @Update("update approve_change_teacher set state=#{state}, opinion = #{opinion} where id=#{id} and manager_id=#{manager_id}")
    Integer approveHandle(@Param("id") int id, @Param("manager_id") int manager_id, @Param("opinion") String opinion, @Param("state") int state);

    /**
     * 菜单列表
     */
    @Select("select me.group_id, me.father_id as parentId, me.id, me.title as name, me.active as checkAll " +
            "from menu me left join manager ma on me.department_id = ma.department_id " +
            "where ma.id = #{manager_id}")
    List<ManageMenu> getMenuList(int manager_id);


    /**
     * 发布通知
     */
    @Insert("insert into news (department_id, create_time, state, author, content, title) " +
            "values ( #{user.department_id}, #{news.create_time}, 1, #{user.name}, #{news.content}, #{news.title})")
    Integer managerPublish(@Param("user") User user, @Param("news") News news);


    /**
     * 获取可操作的教师
     */
    @Select("select st.id, t.name as teacher_name, t.major, st.account, st.num, st.state from select_teacher st" +
            " left join teacher t on st.teacher_id = t.id " +
            " left join manager m on m.department_id = st.department_id" +
            " where m.id = #{manager_id}")
    List<SelectTeacher> getTeacherList(int manager_id);

    /**
     * 获取未发布供学生选择的教师
     */
    @Select("select t.id, t.name as teacher_name, t.major from teacher t " +
            "    left join manager m on m.department_id = t.department_id " +
            "where m.id = #{manager_id}  " +
            "and t.id not in (select teacher_id from select_teacher st left join manager m on st.department_id = m.department_id)")
    List<SelectTeacher> getCanPublishTeacherList(int manager_id);

    /**
     * 发布导师信息
     */
    @Insert("insert into select_teacher (department_id, teacher_id, account, state)" +
            " values (#{department_id}, #{teacher.teacher_id}, #{teacher.account}, #{teacher.state})")
    Integer submitTeacher(@Param("department_id") int department_id, @Param("teacher") SelectTeacher teacher);


    /**
     *  改变供选择的导师的状态，用来控制学生端的可见
     */
    @Update("update select_teacher set state=#{state} where department_id=#{department_id} and id=#{id} ")
    Integer changeSelectTeacherState(@Param("department_id") int department_id, @Param("id") int id, @Param("state") int state);
}
