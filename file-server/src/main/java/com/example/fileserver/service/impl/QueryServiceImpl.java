package com.example.fileserver.service.impl;

import com.example.fileserver.dao.QueryDao;
import com.example.fileserver.pojo.file.ClassFile;
import com.example.fileserver.pojo.file.File;
import com.example.fileserver.pojo.file.GroupFile;
import com.example.fileserver.pojo.file.DepartmentFile;
import com.example.fileserver.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author ltx
 * @Date 16:02 2020/5/23
 */
@Component
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryDao queryDao;

    @Override
    public List<GroupFile> getTeacherFileList(int teacher_id) {
        return queryDao.getTeacherFileList(teacher_id);
    }

    @Override
    public List<ClassFile> getDepartmentFileList(int manager_id) {
        List<ClassFile> fileList = queryDao.getDepartmentFileList(manager_id);
        // 按班级分组
        Map<String, List<ClassFile>> listMap = fileList.stream().collect(Collectors.groupingBy(ClassFile::getStudent_class_name));
        List<ClassFile> resultList = new ArrayList<>(listMap.size());

        // 此行相当于添加班级文件夹
        listMap.forEach((k, v) -> resultList.add(new ClassFile().setFile_name(k).setChildren(v).setUrl("")));
        return resultList;
    }

    @Override
    public List<DepartmentFile> getSchoolFileList() {
        List<DepartmentFile> fileList = queryDao.getSchoolFileList();
        // 1.按学院分组
        Map<String, List<DepartmentFile>> listMap = fileList.stream().collect(Collectors.groupingBy(DepartmentFile::getDepartment_name));
        List<DepartmentFile> resultList = new ArrayList<>(listMap.size());

        listMap.forEach((k, v) -> {
            DepartmentFile departmentGroup = new DepartmentFile();

            Map<String, List<DepartmentFile>> classMap = v.stream().collect(Collectors.groupingBy(DepartmentFile::getStudent_class_name));
            List<DepartmentFile> classList = new ArrayList<>(listMap.size());
            classMap.forEach((key, value) -> classList.add(new DepartmentFile().setFile_name(key).setChildren(value).setUrl("")));

            departmentGroup.setChildren(classList).setFile_name(k);


            resultList.add(departmentGroup);
        });
        return resultList;
    }
}
