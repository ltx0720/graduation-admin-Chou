package com.example.service.pojo;

import lombok.Data;

/**
 * @Author ltx
 * @Date 17:58 2020/5/11
 *
 * 待导师审批
 */
@Data
public class TeacherApprove {
    public int id;
    public int teacher_id;
    public int student_id;
    public int topic_id;
    public int report_id;
    public byte state;
    public String opinion;
    public String title;
    public String student_name;
    public String class_name;
}
