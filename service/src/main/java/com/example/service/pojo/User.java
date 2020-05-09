package com.example.service.pojo;

import lombok.Data;

/**
 * @Author ltx
 * @Date 21:53 2020/4/12
 *
 * 用户
 */
@Data
public class User {
   private byte role;
   private int school_id;
   private int department_id;
   private long number;
   private String name;
   private String phone;
   private String mail;
}
