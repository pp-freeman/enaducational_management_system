package com.pp.educational_management_system.domain;

import java.util.Date;

public class SysToken {
    private String userId;
    private String token;
    private Date expireTime;
    private Date updateTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getToken() {
        return token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "SysToken{" +
                "userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", expireTime=" + expireTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
