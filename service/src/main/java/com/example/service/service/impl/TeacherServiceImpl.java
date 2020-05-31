package com.example.service.service.impl;

import com.example.service.dao.teacher.TeacherDao;
import com.example.service.pojo.Student;
import com.example.service.pojo.TeacherApprove;
import com.example.service.pojo.User;
import com.example.service.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @Author ltx
 * @Date 21:22 2020/5/15
 */
@Component
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao dao;

    @Override
    public List<Student> getAllStudent(User user) {
        return dao.getAllStudent(user.getDepartment_id(), user.getIdentify_id());
    }

    @Override
    public List<TeacherApprove> getTeacherApprove(int teacher_id, String type) {
        if ("pending".equals(type)) {
            return dao.getTeacherApprovePending(teacher_id);
        } else if ("finish".equals(type)) {
            return dao.getTeacherApproveFinished(teacher_id);
        }

        return Collections.emptyList();
    }

    /**
     * 导师审批申请
     * @param id 条目id
     * @param user 需要传入用户对象，以验证是否有权限。防止恶意修改。
     * @param state 想要修改条目后的状态
     * @return boolean
     */
    @Override
    public boolean approveHandle(int id, String opinion, User user, String state) {
        if ("pass".equals(state)) {
            return dao.approveHandle(id, opinion, user.getIdentify_id(), 1) == 1;
        } else if ("refuse".equals(state)) {
            return dao.approveHandle(id, opinion, user.getIdentify_id(),-1) == 1;
        }

        return false;
    }
}
