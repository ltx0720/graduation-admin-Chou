package com.example.fileserver.dao;

import com.example.fileserver.pojo.file.ClassFile;
import com.example.fileserver.pojo.file.GroupFile;
import com.example.fileserver.pojo.file.DepartmentFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author ltx
 * @Date 0:12 2020/5/23
 */
@Mapper
public interface QueryDao {

    /**
     * 获取
     */
    @Select("select f.filename as file_name, f.url from file f left join student s " +
            "on s.student_number = f.identify_number " +
            "where s.teacher_id = #{teacher_id}")
    List<GroupFile> getTeacherFileList(int teacher_id);


    @Select("select f.filename, f.url, s.class_name, s.name from file f left join student s " +
            "on s.student_number = f.identify_number " +
            "left join manager m on s.department_id = m.department_id " +
            "where m.id = #{manager_id}")
    @Results(id = "file", value = {
            @Result(property = "file_name", column = "filename"),
            @Result(property = "student_name", column = "name"),
            @Result(property = "student_class_name", column = "class_name"),
    })
    List<ClassFile> getDepartmentFileList(int manager_id);


    @Select("select f.filename, f.url, s.class_name, s.name, s.department_id, d.name as department_name " +
            "from file f " +
            "left join student s " +
            "on s.student_number = f.identify_number " +
            "left join department d on s.department_id = d.id ")
    @Results(id = "file1", value = {
            @Result(property = "file_name", column = "filename"),
            @Result(property = "student_name", column = "name"),
            @Result(property = "student_class_name", column = "class_name"),
    })
    List<DepartmentFile> getSchoolFileList();
}
