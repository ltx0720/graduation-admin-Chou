package com.example.fileserver.service;

import com.example.fileserver.pojo.file.ClassFile;
import com.example.fileserver.pojo.file.File;
import com.example.fileserver.pojo.file.GroupFile;
import com.example.fileserver.pojo.file.DepartmentFile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ltx
 * @Date 16:02 2020/5/23
 */
@Service
public interface QueryService {
    List<GroupFile> getTeacherFileList(int teacher_id);
    List<ClassFile> getDepartmentFileList(int manager_id);
    List<DepartmentFile> getSchoolFileList();
}
