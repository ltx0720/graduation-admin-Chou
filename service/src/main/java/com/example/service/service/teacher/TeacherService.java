package com.example.service.service.teacher;

import com.example.service.pojo.Student;
import com.example.service.pojo.TeacherApprove;
import com.example.service.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ltx
 * @Date 21:21 2020/5/15
 */
@Service
public interface TeacherService {
    List<Student> getAllStudent(User user);
    List<TeacherApprove> getTeacherApprove(int teacher_id, String type);
    boolean approveHandle(int id, String opinion, User user, String state);
}
