package com.example.service.service.common;

import com.example.service.pojo.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ltx
 * @Date 21:07 2020/5/8
 */
@Service
public interface CommonService {
    List<News> getSimpleNews(int school_id, int student_id);

    News getNewsDetail(int id);
}
