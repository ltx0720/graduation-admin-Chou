package com.example.service.service.impl;

import com.example.service.dao.student.StudentDao;
import com.example.service.pojo.Menu;
import com.example.service.pojo.News;
import com.example.service.pojo.SelectTeacher;
import com.example.service.pojo.Topic;
import com.example.service.service.student.StudentService;
import com.example.service.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author ltx
 * @Date 23:00 2020/5/11
 */
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<News> getSimpleNews(int student_id) {
        return studentDao.getSimpleNews(student_id);
    }

    @Override
    public List<SelectTeacher> getSelectTeacher(int school_id, int department_id) {
        return studentDao.getSelectTeacher(school_id, department_id);
    }

    @Override
    public boolean isSelectedTeacher(int student_id) {
        String teacher_id = studentDao.getSelectedTeacher(student_id);

        return !(teacher_id == null || "".equals(teacher_id));
    }

    @Override
    public List<Topic> getSelectTopicList(int student_id) {
        return studentDao.getSelectTopicList(student_id);
    }

    @Override
    public boolean selectTeacher(int student_id, int teacher_id) {
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("student_id", 1);
        map.put("teacher_id", 1);
        map.put("result", true);
        studentDao.seletTeacher(map);

        return (boolean) map.get("result");
    }

    @Override
    public boolean selectTopic(int student_id, int topic_id) {
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("student_id", student_id);
        map.put("topic_id", topic_id);
        map.put("result", true);

        studentDao.seletTopic(map);
        return (boolean) map.get("result");
    }

    @Override
    public String getStudentMenu(int roles) {
        List<Menu> menuList = studentDao.getStudentMenu(roles);
        List<Map<String, Object>> list = new ArrayList<>();

        for (Menu menu : menuList){
           Map map = new HashMap<String, Object>(1);
           int father_id = menu.getFather_id();
           if (father_id == 0){
               List<Menu> childrenMenu = menuList.stream().filter(m -> m.getFather_id() == father_id).collect(Collectors.toList());

               map.put("path", menu.getPath());
               map.put("component", menu.getComponent());
               map.put("children", childrenMenu);
           }
           list.add(map);
        }

        return GsonUtil.toJson(list);
    }

    @Override
    public boolean isSelectedTopic(int student_id) {
        String topic_id = studentDao.getSelectedTopic(student_id);
        return !(topic_id == null || "".equals(topic_id));
    }
}
