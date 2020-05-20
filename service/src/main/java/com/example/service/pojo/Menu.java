package com.example.service.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author ltx
 * @Date 22:46 2020/5/11
 *
 * 动态功能菜单
 */
@Data
public class Menu {
    public int id;
    public int group_id;
    public int father_id;
    public int roles;
    public boolean active;
    public String name;
    public String path;
    public String component;
    public Meta meta;
    public List<Menu> children;


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

    public int getFather_id() {
        return father_id;
    }

    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", father_id=" + father_id +
                ", roles=" + roles +
                ", active=" + active +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", meta=" + meta +
                ", children=" + children +
                '}';
    }
}
