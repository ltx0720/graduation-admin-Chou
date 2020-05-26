package com.example.fileserver.pojo.file;

import java.util.List;

/**
 * @Author ltx
 * @Date 15:46 2020/5/23
 *
 * 用于显示院级文件
 */
public class ClassFile implements File {
    public int id;
    public String file_name;
    public String student_name;
    public String student_class_name;
    public String url;
    public List<ClassFile> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public ClassFile setFile_name(String file_name) {
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

    public ClassFile setStudent_class_name(String student_class_name) {
        this.student_class_name = student_class_name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ClassFile setUrl(String url) {
        this.url = url;
        return this;
    }


    public List<ClassFile> getChildren() {
        return children;
    }

    public ClassFile setChildren(List<ClassFile> children) {
        this.children = children;
        return this;
    }
}
