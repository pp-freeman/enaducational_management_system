package com.pp.educational_management_system.service.impl;

import com.pp.educational_management_system.dao.IRoleDao;
import com.pp.educational_management_system.domain.Permission;
import com.pp.educational_management_system.domain.PermissionButton;
import com.pp.educational_management_system.domain.PermissionChild;
import com.pp.educational_management_system.domain.Role;
import com.pp.educational_management_system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    IRoleDao roleDao;


    @Override
    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }

    @Override
    public List<Role> getRoleList() {
        return roleDao.getRoleList();
    }

    @Override
    public List<PermissionChild> getPermissionList(String id) {
        return roleDao.getPermissionList(id);
    }

    public List<Permission> getAllRoleList(){
        return roleDao.getAllRoleList();
    }
    //得到所有一级菜单
    public List<Permission> getAllPermission(){
        return roleDao.getAllPermission();
    }
    //得到所有二级菜单
    public List<PermissionChild> getAllPermissionChild(){
        return roleDao.getAllPermissionChild();
    }
    //得到所有三级菜单
    public List<PermissionButton> getAllPermissionButton(){
        return roleDao.getAllPermissionButton();
    }
    //删除已有菜单权限
    public int deletePermissionChild(String roleId){
        return roleDao.deletePermissionChild(roleId);
    }
    //删除已有按钮权限
    public int deletePermissionButton(String roleId){
        return roleDao.deletePermissionButton(roleId);
    }
    //修改菜单权限
    public int updatePermissionChild(String roleId,List<String>idList){
        return roleDao.updatePermissionChild(roleId,idList);
    }
    //修改按钮权限
    public int updatePermissionButton(String roleId,List<String>idList){
        return roleDao.updatePermissionButton(roleId,idList);
    }

    @Override
    public void deleteUserRole(String userId) {
        roleDao.deleteUserRole(userId);
    }

    @Override
    public void updateUserRole(String userId, String roleId) {
        roleDao.updateUserRole(userId,roleId);
    }

    public int addRole(Role role){
        return roleDao.addRole(role);
    }

    public Role getUpdateRole(String id){
        return roleDao.getUpdateRole(id);
    }

    //修改角色
    public int updateRole(Role role){
        return roleDao.updateRole(role);
    }
    //删除角色
    public int deleteRole(String id) {
        return roleDao.deleteRole(id);
    }
}
