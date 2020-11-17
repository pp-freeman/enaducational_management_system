package com.pp.educational_management_system.domain;

import java.util.List;

public class PermissionChild {
    private String id;
    private String permissionName;
    private String url;
    private List<PermissionButton> permissionButtons;
    private List<PermissionButton> children;


    public List<PermissionButton> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionButton> children) {
        this.children = children;
    }

    public List<PermissionButton> getPermissionButtons() {
        return permissionButtons;
    }

    public void setPermissionButtons(List<PermissionButton> permissionButtons) {
        this.permissionButtons = permissionButtons;
    }

    @Override
    public String toString() {
        return "PermissionChild{" +
                "id='" + id + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                ", permissionButtons=" + permissionButtons +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }



    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public String getUrl() {
        return url;
    }
}
