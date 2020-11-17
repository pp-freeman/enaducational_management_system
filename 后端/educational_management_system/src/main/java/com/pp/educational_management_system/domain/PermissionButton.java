package com.pp.educational_management_system.domain;

public class PermissionButton {
    private String id;
    private String permissionName;
    private String url;

    @Override
    public String toString() {
        return "PermissonButton{" +
                "id='" + id + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
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
