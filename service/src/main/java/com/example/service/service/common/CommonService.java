package com.example.service.service.common;

import com.example.service.pojo.Menu;
import com.example.service.pojo.News;
import com.example.service.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author ltx
 * @Date 21:07 2020/5/8
 */
@Service
public interface CommonService {

    List<Map<String, Object>> getMenuList(int department_id, int roles);

    List<News> getSimpleNews(int school_id, int department_id);

    News getNewsDetail(int id);

    Integer teacherPublishNews(User user, News news);

    List<News> getSimpleNewsTeacher(User user);
}
