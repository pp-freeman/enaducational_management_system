package com.pp.educational_management_system.domain;

import java.util.List;

public class UserInfo {
    private String num;
    private String name;
    private String sex;
    private String academy;
    private String phone;
    private String id; //身份证号
    private String polotics;

    public UserInfo() {
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPolotics(String polotics) {
        this.polotics = polotics;
    }

    public String getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getAcademy() {
        return academy;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

    public String getPolotics() {
        return polotics;
    }

    public UserInfo(String num, String name, String sex, String academy, String phone, String id, String polotics) {
        this.num = num;
        this.name = name;
        this.sex = sex;
        this.academy = academy;
        this.phone = phone;
        this.id = id;
        this.polotics = polotics;
    }
}
