package com.example.service.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author ltx
 * @Date 16:36 2020/5/8
 */
@Data
public class News {
    private int id;
    private String content;
    private int school_id;
    private int user_id;
    private Date create;
    private String author;
}
