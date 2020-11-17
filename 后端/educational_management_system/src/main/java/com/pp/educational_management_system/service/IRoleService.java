package com.pp.educational_management_system.service;

import com.pp.educational_management_system.domain.Permission;
import com.pp.educational_management_system.domain.PermissionButton;
import com.pp.educational_management_system.domain.PermissionChild;
import com.pp.educational_management_system.domain.Role;

import java.util.List;

public interface IRoleService {
    //只得到权限列表，不包含子权限
    public List<Role> getAllRole();
    public List<Role> getRoleList();

    public List<PermissionChild> getPermissionList(String id);

    public List<Permission> getAllRoleList();

    //得到所有一级菜单
    public List<Permission> getAllPermission();
    //得到所有二级菜单
    public List<PermissionChild> getAllPermissionChild();
    //得到所有三级菜单
    public List<PermissionButton> getAllPermissionButton();
    //删除已有菜单权限
    public int deletePermissionChild(String roleId);
    //删除已有按钮权限
    public int deletePermissionButton(String roleId);
    //修改菜单权限
    public int updatePermissionChild(String roleId,List<String>idList);
    //修改按钮权限
    public int updatePermissionButton(String roleId,List<String>idList);
    public void deleteUserRole(String userId);

    public void updateUserRole(String userId,String roleId);
    //添加角色
    public int addRole(Role role);
    //得到修改的角色
    public Role getUpdateRole(String id);
    //修改角色
    public int updateRole(Role role);
    //删除角色
    public int deleteRole(String id);
}
