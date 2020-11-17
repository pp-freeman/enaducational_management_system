package com.pp.educational_management_system.domain;

import java.util.List;

public class Permission {
    private String id;
    private String permissionName;
    private String url;
    private List<PermissionChild> permissionChildren;
    private List<PermissionChild> children;




    public void setPermissionChildren(List<PermissionChild> permissionChildren) {
        this.permissionChildren = permissionChildren;
    }

    public List<PermissionChild> getPermissionChildren() {
        return permissionChildren;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                ", permissionChildren=" + permissionChildren +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChildren(List<PermissionChild> children) {
        this.children = children;
    }

    public List<PermissionChild> getChildren() {
        return children;
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
