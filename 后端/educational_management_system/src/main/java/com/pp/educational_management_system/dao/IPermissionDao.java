package com.pp.educational_management_system.dao;

import com.pp.educational_management_system.domain.Permission;
import com.pp.educational_management_system.domain.PermissionButton;
import com.pp.educational_management_system.domain.PermissionChild;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {
    public List<Permission> getAllPermission();
    public List<Permission> getAllPermissionChild();
    public List<Permission> getAllPermissionButton();
}
