package com.pp.educational_management_system.service.impl;

import com.pp.educational_management_system.dao.IUserDao;
import com.pp.educational_management_system.domain.User;
import com.pp.educational_management_system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public User login(String username, String password) {
        User user = userDao.getUserByMessage(username,password);
        return user;
    }

    @Override
    public List<User> getAllUser(String username,String roleName,int pageStart,int pageSize) {
        return userDao.getAllUser(username,roleName,pageStart,pageSize);
    }

    @Override
    public int getAllUserCount(String username, String roleName) {
        return userDao.getAllUserCount(username,roleName);
    }

    @Override
    public int updateUserState(String id, int state) {
        return userDao.updateUserState(id,state);
    }

    @Override
    public int addUser(String id, String username, String password) {
        return userDao.addUser(id,username,password);
    }

    @Override
    public int deleteUser(String id) {
        return userDao.deleteUser(id);
    }

    @Override
    public User getUpdateUser(String id) {
        return userDao.getUpdateUser(id);
    }

    @Override
    public int editUser(User user) {
        return userDao.editUser(user);
    }
}
