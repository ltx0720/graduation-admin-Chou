package com.example.service.service.impl;

import com.example.service.dao.manager.ManagerDao;
import com.example.service.pojo.ChangeTeacherApprove;
import com.example.service.pojo.News;
import com.example.service.pojo.TeacherApprove;
import com.example.service.pojo.User;
import com.example.service.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @Author ltx
 * @Date 10:54 2020/5/19
 */
@Component
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Override
    public List<News> getSimpleNewsManager(int department_id) {
        return managerDao.getSimpleNewsManager(department_id);
    }

    @Override
    public List<ChangeTeacherApprove> getChangeTeacherApprove(int manager_id, String type) {
        if ("pending".equals(type)) {
            return managerDao.getTeacherApprovePending(manager_id);
        } else if ("finish".equals(type)) {
            return managerDao.getTeacherApproveFinished(manager_id);
        }

        return Collections.emptyList();
    }

    @Override
    public boolean approveHandle(int id, User user, String state){
        if ("pass".equals(state)) {
            return managerDao.approveHandle(id, user.getIdentify_id(), 1) == 1;
        } else if ("refuse".equals(state)) {
            return managerDao.approveHandle(id, user.getIdentify_id(),-1) == 1;
        }

        return false;
    }
}
