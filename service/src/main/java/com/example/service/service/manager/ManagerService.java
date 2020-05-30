package com.example.service.service.manager;

import com.example.service.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author ltx
 * @Date 10:53 2020/5/19
 */
@Service
public interface ManagerService {
    boolean managerPublish(User user, News news);
    List<News> getSimpleNewsManager(int department_id);
    List<ChangeTeacherApprove> getChangeTeacherApprove(int manager_id, String type);
    boolean approveHandle(int id, User user, String opinion, String action);
    Map<Integer, List<ManageMenu>> getMenuList(int manager_id);
    List<SelectTeacher> getTeacherList(int manager_id);
    List<SelectTeacher> getCanPublishTeacherList(int manager_id);
    boolean submitTeacher(int department_id, SelectTeacher teacher);
    boolean changeSelectTeacherState(int department_id,int id, int state);
}
