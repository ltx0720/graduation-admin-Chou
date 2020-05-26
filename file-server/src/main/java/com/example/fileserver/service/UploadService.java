package com.example.fileserver.service;

import com.example.fileserver.pojo.file.GroupFile;
import org.springframework.stereotype.Service;

/**
 * @Author ltx
 * @Date 23:42 2020/5/22
 */
@Service
public interface UploadService {

    boolean witeUploadRecord(int identify_id, GroupFile groupFile);

}
