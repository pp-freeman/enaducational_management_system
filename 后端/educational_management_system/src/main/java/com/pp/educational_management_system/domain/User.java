package com.pp.educational_management_system.domain;


import java.util.List;
import java.util.UUID;

/**
 * 用户实体
 */
public class User {
    private String id;
    private String username;
    private String password;
    private int state;
    private String role;
    private List<Role> roles;


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", role='" + role + '\'' +
                ", roles=" + roles +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getState() {
        return state;
    }

    public String getRole() {
        return role;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
