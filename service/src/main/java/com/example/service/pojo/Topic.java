package com.example.service.pojo;

import lombok.Data;

/**
 * @Author ltx
 * @Date 16:52 2020/5/11
 *
 * 课题
 */
@Data
public class Topic {
   public int id;
   public int school_id;
   public int department_id;
   public int teacher_id;
   public String title;
   public String direction;
}
