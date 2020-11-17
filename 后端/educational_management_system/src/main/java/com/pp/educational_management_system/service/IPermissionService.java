package com.pp.educational_management_system.service;

import com.pp.educational_management_system.domain.Permission;
import com.pp.educational_management_system.domain.PermissionButton;
import com.pp.educational_management_system.domain.PermissionChild;

import java.util.List;

public interface IPermissionService {
    public List<Permission> getAllPermission();
    public List<Permission> getAllPermissionChild();
    public List<Permission> getAllPermissionButton();
}
