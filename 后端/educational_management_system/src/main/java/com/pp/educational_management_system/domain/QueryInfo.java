package com.pp.educational_management_system.domain;

public class QueryInfo {
    private String number;
    private String username;
    private String department;
    private int pageNum;
    private int pagesize;

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getDepartment() {
        return department;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getUsername() {
        return username;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPagesize() {
        return pagesize;
    }
}
