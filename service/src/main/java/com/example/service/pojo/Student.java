package com.example.service.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author ltx
 * @Date 17:30 2020/5/11
 */
@Data
@Component
public class Student {
    public int id;
    public int user_id;
    public int school_id;
    public int department_id;
    public int teacher_id;
    public int class_id;
    public String name;
    public String class_name;
    public String topic_title;
    public String topic;
}
