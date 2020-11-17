package com.pp.educational_management_system.service;

import com.pp.educational_management_system.domain.User;

import java.util.List;

public interface IUserService {
    //登录
    public User login(String username,String password);

    //得到所有用户
    public List<User> getAllUser(String username,String roleName,int pageStart,int pageSize);

    public int getAllUserCount(String username,String roleName);

    public int updateUserState(String id,int state);

    public int addUser(String id,String username,String password);

    public int deleteUser(String id);

    public User getUpdateUser(String id);

    public int editUser(User user);
}
