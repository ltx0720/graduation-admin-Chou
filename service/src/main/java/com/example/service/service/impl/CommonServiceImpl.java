package com.example.service.service.impl;

import com.example.service.dao.common.CommonDao;
import com.example.service.pojo.News;
import com.example.service.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author ltx
 * @Date 21:07 2020/5/8
 */
@Component
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDao dao;

    @Override
    public List<News> getSimpleNews(int school_id, int student_id) {
        List<News> news = dao.getSimpleNews(school_id, student_id);

        return news;
    }

    @Override
    public News getNewsDetail(int id) {
        News news = dao.getNewsDetail(id);
        return news;
    }
}
