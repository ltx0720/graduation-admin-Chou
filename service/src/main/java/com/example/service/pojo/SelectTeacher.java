package com.example.service.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author ltx
 * @Date 15:55 2020/5/11
 *
 * 供学生选择的导师
 */
@Data
@Component
public class SelectTeacher {
    public int id;
    public int teacher_id;
    public String teacher_name;
    public String information;
    public String major;
    public int num;
}
