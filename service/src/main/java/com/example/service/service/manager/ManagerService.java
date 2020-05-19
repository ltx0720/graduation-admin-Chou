package com.example.service.service.manager;

import com.example.service.pojo.ChangeTeacherApprove;
import com.example.service.pojo.News;
import com.example.service.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ltx
 * @Date 10:53 2020/5/19
 */
@Service
public interface ManagerService {
    List<News> getSimpleNewsManager(int department_id);
    List<ChangeTeacherApprove> getChangeTeacherApprove(int manager_id, String type);
    boolean approveHandle(int id, User user, String state);
}
