package com.pp.educational_management_system.dao;

import com.pp.educational_management_system.domain.SysToken;
import org.springframework.stereotype.Repository;

@Repository
public interface ISysTokenDao {

    //根据用户id获得token
    public SysToken findTokenByUserId(String userId);

    //保存token
    public void save(SysToken sysToken);

    public void update(SysToken sysToken);

    public SysToken findByToken(String accessToken);
}
