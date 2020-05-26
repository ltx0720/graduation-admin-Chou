package com.example.fileserver.pojo.file;

import java.util.List;

/**
 * @Author ltx
 * @Date 15:46 2020/5/23
 */
public class DepartmentFile implements File {
    public int id;
    public String department_name;
    public String file_name;
    public String student_name;
    public String student_class_name;
    public String url;
    public List<DepartmentFile> children;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public DepartmentFile setDepartment_name(String department_name) {
        this.department_name = department_name;
        return this;
    }

    public String getFile_name() {
        return file_name;
    }

    public DepartmentFile setFile_name(String file_name) {
        this.file_name = file_name;
        return this;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_class_name() {
        return student_class_name;
    }

    public void setStudent_class_name(String student_class_name) {
        this.student_class_name = student_class_name;
    }

    public String getUrl() {
        return url;
    }

    public DepartmentFile setUrl(String url) {
        this.url = url;
        return this;
    }


    public List<DepartmentFile> getChildren() {
        return children;
    }

    public DepartmentFile setChildren(List<DepartmentFile> children) {
        this.children = children;
        return this;
    }
}
