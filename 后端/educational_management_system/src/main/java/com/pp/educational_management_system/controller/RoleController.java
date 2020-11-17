package com.pp.educational_management_system.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.pp.educational_management_system.domain.*;
import com.pp.educational_management_system.service.IRoleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.ObjectName;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @RequestMapping("/getAllRole")
    public String getAllRole(){
        List<Role> roles = roleService.getAllRole();
        HashMap<String, Object> result = new HashMap<String, Object>();
        if(roles.size()==0){
            result.put("state",404);
        }else{
            result.put("state",200);
        }
        result.put("data",roles);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/getRoleList")
    public String getRoleList(){
        List<Role> roles = roleService.getRoleList();

        for(int i=0;i<roles.size();i++){
            List<PermissionChild> permissionChildren = roleService.getPermissionList(roles.get(i).getId());

            if(permissionChildren.size()!=0){
                List<Permission> permissions = roles.get(i).getPermissions();
                for(int j=0;j<permissions.size();j++){
                    List<PermissionChild> rPermissionChildren  = permissions.get(j).getPermissionChildren();

                    for(int k=0;k<rPermissionChildren.size();k++){

                        for(int m=0;m<permissionChildren.size();m++){
                            if(permissionChildren.get(m)!=null) {
                                if (rPermissionChildren.get(k).getPermissionName().equals(permissionChildren.get(m).getPermissionName())) {
                                    rPermissionChildren.get(k).setPermissionButtons(permissionChildren.get(m).getPermissionButtons());
                                }
                            }
                        }
                    }
                }
            }
        }
//        System.out.println(roles);
        for(Role r :roles){
            r.setChildren(r.getPermissions());
            for(Permission p : r.getPermissions()){
                p.setChildren(p.getPermissionChildren());
                for(PermissionChild pc : p.getPermissionChildren()){
                    pc.setChildren(pc.getPermissionButtons());
                }
            }
        }

        HashMap<String, Object> result = new HashMap<String, Object>();
        if(roles.size()==0){
            result.put("state",404);
        }else{
            result.put("state",200);
        }
        result.put("data",roles);
        return JSON.toJSONString(result);
    }

    @RequestMapping("getAllRoleList")
    public String getAllRoleList(){
        List<Permission> permissions = roleService.getAllRoleList();
        for(Permission p : permissions){
            p.setChildren(p.getPermissionChildren());
            for(PermissionChild pc : p.getPermissionChildren()){
                pc.setChildren(pc.getPermissionButtons());
            }
        }
        Map<String,Object> result = new HashMap<>();
        if(permissions.size()==0){
            result.put("state",404);
        }else{
            result.put("state",200);
        }
        result.put("data",permissions);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/updatePermission")
    public String updatePermission(@RequestParam("roleId")String roleId, @RequestParam("idStr") String idStr){
        List<String> permissionButtonList = new ArrayList<>();
        List<String> permissionChildList = new ArrayList<>();
        String real[] = idStr.split(",");
        List<String> permissionChildName = new ArrayList<>();
        for(PermissionChild p : roleService.getAllPermissionChild()){
            permissionChildName.add(p.getPermissionName());
        }
        List<String> permissionButtonName = new ArrayList<>();
        for(PermissionButton p : roleService.getAllPermissionButton()){
            permissionButtonName.add(p.getPermissionName());
        }
        for(int i=0;i<real.length;i++){
            if(permissionChildName.contains(real[i])){
                permissionChildList.add(real[i]);
            }
            if(permissionButtonName.contains(real[i])){
                permissionButtonList.add(real[i]);
            }
        }
        roleService.deletePermissionChild(roleId);
        roleService.deletePermissionButton(roleId);
        if(permissionButtonList.size()!=0){
            roleService.updatePermissionChild(roleId,permissionChildList);
        }
        if(permissionChildList.size()!=0){
            roleService.updatePermissionButton(roleId,permissionButtonList);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("state",200);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/updateRole")
    public String updateRole(String userId,String roleId){
        roleService.deleteUserRole(userId);
        roleService.updateUserRole(userId,roleId);
        Map<String,Object> result = new HashMap<>();
        result.put("state",200);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/addRole")
    public String addRole(@RequestBody Role role){
        role.setId(UUID.randomUUID().toString());
        int i = roleService.addRole(role);
        HashMap<String,Object> result = new HashMap<>();
        if(i==0){
            result.put("state",404);
        }else{
            result.put("state",200);
        }
        return JSON.toJSONString(result);
    }

    @RequestMapping("/getUpdateRole")
    public String getUpdateRole(String id){
        Role role = roleService.getUpdateRole(id);
        return JSON.toJSONString(role);
    }

    @RequestMapping("/editRole")
    public String editRole(@RequestBody Role role){
        int i = roleService.updateRole(role);
        HashMap<String,Object> result = new HashMap<>();
        if(i==0){
            result.put("state",404);
        }else{
            result.put("state",200);
        }
        return JSON.toJSONString(result);
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(String id){
        int i = roleService.deleteRole(id);
        roleService.deletePermissionButton(id);
        roleService.deletePermissionChild(id);
        HashMap<String,Object> result = new HashMap<>();
        if(i==0){
            result.put("state",404);
        }else{
            result.put("state",200);
        }
        return JSON.toJSONString(result);
    }
}
