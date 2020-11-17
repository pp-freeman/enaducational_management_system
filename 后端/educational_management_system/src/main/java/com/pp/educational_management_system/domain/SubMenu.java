package com.pp.educational_management_system.domain;

public class SubMenu {
    private String id;
    private String title;
    private String path;

    @Override
    public String toString() {
        return "SubMenu{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
