package com.example.fileserver.pojo.file;

import java.util.List;

/**
 * @Author ltx
 * @Date 23:39 2020/5/22
 *
 * 文件信息
 */
public class GroupFile implements File {
    public int id;
    public int identify_number;
    public int type_id;
    public String file_name;
    public String bucket;
    public String url;
    public List<GroupFile> children;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentify_number() {
        return identify_number;
    }

    public void setIdentify_number(int identify_number) {
        this.identify_number = identify_number;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<GroupFile> getChildren() {
        return children;
    }

    public void setChildren(List<GroupFile> children) {
        this.children = children;
    }
}
