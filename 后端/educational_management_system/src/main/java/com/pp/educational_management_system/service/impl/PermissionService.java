package com.pp.educational_management_system.service.impl;

import com.pp.educational_management_system.dao.IPermissionDao;
import com.pp.educational_management_system.domain.Permission;
import com.pp.educational_management_system.domain.PermissionButton;
import com.pp.educational_management_system.domain.PermissionChild;
import com.pp.educational_management_system.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    IPermissionDao permissionDao;

    @Override
    public List<Permission> getAllPermission() {
        return permissionDao.getAllPermission();
    }

    @Override
    public List<Permission> getAllPermissionChild() {
        return permissionDao.getAllPermissionChild();
    }

    @Override
    public List<Permission> getAllPermissionButton() {
        return permissionDao.getAllPermissionButton();
    }
}
