package com.pp.educational_management_system.service;

import com.pp.educational_management_system.domain.SysToken;
import com.pp.educational_management_system.domain.User;

import java.util.Map;

public interface IShiroService {

    public User getUserByName(String username);

    public Map<String, Object> createToken(String userId);

    public void logout(String token);

    public SysToken findByToken(String accessToken);

    public User findByUserId(String userId);
}
