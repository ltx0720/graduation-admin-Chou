package com.example.service.service.impl;

import com.example.service.dao.common.CommonDao;
import com.example.service.pojo.Menu;
import com.example.service.pojo.News;
import com.example.service.pojo.User;
import com.example.service.service.common.CommonService;
import com.example.service.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author ltx
 * @Date 21:07 2020/5/8
 */
@Component
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDao dao;

    @Override
    public List<Map<String, Object>> getMenuList(int department_id, int roles) {
        List<Menu> menuList = dao.getMenuList(department_id, roles);
        List<Map<String, Object>> list = new ArrayList<>();

        for (Menu menu : menuList) {
            int father_id = menu.getFather_id();
            if (father_id == 0) {
                Map map = new LinkedHashMap(1);
                List<Menu> childrenMenu = menuList.stream().filter(m -> m.getFather_id() == menu.id).collect(Collectors.toList());

                map.put("path", menu.getPath());
                map.put("component", menu.getComponent());
                map.put("meta", menu.getMeta());
                map.put("children", childrenMenu);
                list.add(map);
            }
        }

        return list;
    }


    //    @Override
//    public List<Menu> getMenuList(int manager_id, int roles) {
//        List<Menu> menuList = dao.getMenuList(manager_id, roles);
//        List<Map<String, Object>> list = new ArrayList<>();
//
//        for (Menu menu : menuList){
//            Map map = new LinkedHashMap(1);
//            int father_id = menu.getFather_id();
//            if (father_id == 0){
//                List<Menu> childrenMenu = menuList.stream().filter(m -> m.getFather_id() == father_id).collect(Collectors.toList());
//
//                map.put("path", menu.getPath());
//                map.put("component", menu.getComponent());
//                map.put("children", childrenMenu);
//            }
//            list.add(map);
//        }
//
//        return GsonUtil.toJson(list);
//    }

    @Override
    public News getNewsDetail(int id) {
        News news = dao.getNewsDetail(id);
        return news;
    }

    @Override
    public List<News> getSimpleNews(int school_id, int department_id) {
        List<News> news = dao.getSimpleNews(school_id, department_id);
        return news;
    }

    @Override
    public Integer teacherPublishNews(User user, News news) {
        return dao.teacherPublishNews(user, news);
    }


    @Override
    public List<News> getSimpleNewsTeacher(User user) {
        List<News> newsList = dao.getSimpleNewsTeacher(user.getDepartment_id(), user.getIdentify_id());
        return newsList;
    }

    @Override
    public String getNewsContent(int department_id, int id) {
        return dao.getNewsContent(department_id, id);
    }

}
