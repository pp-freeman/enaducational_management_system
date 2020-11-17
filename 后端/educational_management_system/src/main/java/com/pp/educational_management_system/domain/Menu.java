package com.pp.educational_management_system.domain;

import java.util.List;

public class Menu {
    private String id;
    private String title;
    private String path;
    private List<SubMenu> menuList;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", menuList=" + menuList +
                '}';
    }

    public void setMenuList(List<SubMenu> menuList) {
        this.menuList = menuList;
    }

    public List<SubMenu> getMenuList() {
        return menuList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
