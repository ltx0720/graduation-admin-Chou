package com.example.service.pojo;

import java.util.List;

/**
 * @Author ltx
 * @Date 21:20 2020/5/20
 */
public class ManageMenu {
    public int id;
    public int group_id;
    public int parentId;
    public int type;
    public boolean checkAll;
    public String name;
    public List<ManageMenu> children;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isCheckAll() {
        return checkAll;
    }

    public void setCheckAll(boolean checkAll) {
        this.checkAll = checkAll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ManageMenu> getChildren() {
        return children;
    }

    public void setChildren(List<ManageMenu> children) {
        this.children = children;
    }
}
