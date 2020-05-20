package com.example.service.service.manager;

import com.example.service.pojo.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author ltx
 * @Date 10:53 2020/5/19
 */
@Service
public interface ManagerService {
    List<News> getSimpleNewsManager(int department_id);
    List<ChangeTeacherApprove> getChangeTeacherApprove(int manager_id, String type);
    boolean approveHandle(int id, User user, String opinion, String action);
    Map<Integer, List<ManageMenu>> getMenuList(int manager_id);
}
