package com.example.service.service.student;

import com.example.service.pojo.Menu;
import com.example.service.pojo.News;
import com.example.service.pojo.SelectTeacher;
import com.example.service.pojo.Topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author ltx
 * @Date 22:58 2020/5/11
 */
@Service
public interface StudentService {

    /**
     * 获取消息通知列表
     */
    List<News> getSimpleNews(int student_id);

    /**
     * 可供选择的导师
     */
    List<SelectTeacher> getSelectTeacher(int school_id, int department_id);

    /**
     * 是否已选择导师
     */
    boolean isSelectedTeacher(int student_id);

    /**
     * 选择导师
     */
    boolean selectTeacher(int student_id, int teacher_id);

    /**
     * 可供选择的课题
     */
    List<Topic> getSelectTopicList(int student_id);

    /**
     * 是否已选择课题
     */
    boolean isSelectedTopic(int student_id);

    /**
     * 选择课题
     */
    boolean selectTopic(int student_id, int topic_id);


    /**
     * 获取学生可见的所有功能菜单
     */
    String getStudentMenu(int roles);

}
