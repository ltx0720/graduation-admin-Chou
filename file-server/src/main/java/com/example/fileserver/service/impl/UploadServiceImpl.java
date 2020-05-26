package com.example.fileserver.service.impl;

import com.example.fileserver.dao.UploadDao;
import com.example.fileserver.pojo.file.GroupFile;
import com.example.fileserver.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author ltx
 * @Date 23:42 2020/5/22
 */
@Component
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadDao uploadDao;


    @Override
    public boolean witeUploadRecord(int identify_id, GroupFile file) {
        Integer row = uploadDao.witeUploadRecord(identify_id, file);
        return row == 1;
    }
}
