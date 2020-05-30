package com.example.service.service.impl;

import com.example.service.dao.manager.ManagerDao;
import com.example.service.pojo.*;
import com.example.service.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

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
    public boolean approveHandle(int id, User user, String opinion, String action) {
        if ("pass".equals(action)) {
            return managerDao.approveHandle(id, user.getIdentify_id(), opinion, 1) == 1;
        } else if ("refuse".equals(action)) {
            return managerDao.approveHandle(id, user.getIdentify_id(),opinion, -1) == 1;
        }

        return false;
    }


    @Override
    public Map<Integer, List<ManageMenu>> getMenuList(int manager_id) {

        /**
         * 1. List<Menu> -> Map<Interger, List>   key:group_id, value:属于该分组的list
         * 2. 对 Map<Interger, List> 里的每组进行子父级聚合。
         */
        List<ManageMenu> menuList = managerDao.getMenuList(manager_id);
        Map<Integer, List<ManageMenu>> listMap = menuList.stream().collect(Collectors.groupingBy(ManageMenu::getGroup_id));

        // 子父级聚合
        listMap.forEach((k, v) -> {
            List<ManageMenu> parentList = new ArrayList<>();

            List<ManageMenu> finalV = v;
            finalV.stream().filter(menu -> menu.getParentId()==0).forEach(menu -> {
                List<ManageMenu> childList = finalV.stream().filter(m -> {
                    if (m.getParentId() == menu.id){
                        m.setType(1);
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                menu.setChildren(childList);
                parentList.add(menu);
            });

            listMap.put(k, parentList);
        });

        return listMap;
    }

    @Override
    public boolean managerPublish(User user, News news) {
        Integer row = managerDao.managerPublish(user, news);
        return row == 1;
    }

    @Override
    public List<SelectTeacher> getTeacherList(int manager_id) {
        return managerDao.getTeacherList(manager_id);
    }

    @Override
    public List<SelectTeacher> getCanPublishTeacherList(int manager_id) {
        return managerDao.getCanPublishTeacherList(manager_id);
    }

    @Override
    public boolean submitTeacher(int department_id, SelectTeacher teacher) {
        return managerDao.submitTeacher(department_id, teacher) == 1;
    }

    @Override
    public boolean changeSelectTeacherState(int department_id, int id, int state) {
        return managerDao.changeSelectTeacherState(department_id, id, state) == 1;
    }
}
