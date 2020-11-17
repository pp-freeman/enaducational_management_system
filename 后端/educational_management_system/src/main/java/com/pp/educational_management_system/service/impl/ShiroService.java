package com.pp.educational_management_system.service.impl;

import com.pp.educational_management_system.dao.ISysTokenDao;
import com.pp.educational_management_system.dao.IUserDao;
import com.pp.educational_management_system.domain.SysToken;
import com.pp.educational_management_system.domain.User;
import com.pp.educational_management_system.service.IShiroService;
import com.pp.educational_management_system.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShiroService implements IShiroService {


    @Autowired
    private IUserDao userDao;
    @Autowired
    private ISysTokenDao sysTokenDao;

    /**
     * 根据username查找用户
     *
     * @param username
     * @return User
     */
    @Override
    public User getUserByName(String username) {
        User user = userDao.getUserByName(username);
        return user;
    }

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    /**
     * 生成一个token
     *@param  [userId]
     *@return Result
     */
    public Map<String, Object> createToken(String userId) {
        Map<String, Object> result = new HashMap<>();
        //生成一个token
        String token = TokenGenerator.generateValue();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        //判断是否生成过token
        SysToken tokenEntity = sysTokenDao.findTokenByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysToken();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //保存token
            sysTokenDao.save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            sysTokenDao.update(tokenEntity);
        }
        result.put("token", token);
        result.put("expire", EXPIRE);
        return result;
    }

    @Override
    public void logout(String token) {
        SysToken byToken = findByToken(token);
        //生成一个token
        token = TokenGenerator.generateValue();
        //修改token
        SysToken tokenEntity = new SysToken();
        tokenEntity.setUserId(byToken.getUserId());
        tokenEntity.setToken(token);
        sysTokenDao.save(tokenEntity);
    }

    @Override
    public SysToken findByToken(String accessToken) {
        return sysTokenDao.findByToken(accessToken);

    }

    @Override
    public User findByUserId(String userId) {
        return userDao.findUserById(userId);
    }
}