package com.pp.educational_management_system.service;

import com.pp.educational_management_system.domain.StudentInfo;
import com.pp.educational_management_system.domain.UserInfo;

import java.util.List;

public interface IUserInfoService {
    public List<UserInfo> getUserInfoList(int pageNum,int pageSize,String username,String department,String number);

    public int getUserInfoCount(String username,String department,String number);
}
