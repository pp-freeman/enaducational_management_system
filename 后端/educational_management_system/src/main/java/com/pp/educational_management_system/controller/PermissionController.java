package com.pp.educational_management_system.controller;

import com.alibaba.fastjson.JSON;
import com.pp.educational_management_system.domain.Permission;
import com.pp.educational_management_system.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    IPermissionService permissionService;

    @RequestMapping("/getAllPermission")
    public String getAllPermission(String level){
        List<Permission> list = new ArrayList<>();
        if (level == null || level.equals("")) {
            list.addAll(permissionService.getAllPermission());
            list.addAll(permissionService.getAllPermissionChild());
            list.addAll(permissionService.getAllPermissionButton());
        }else{
            if(level.equals("1")){
                list.addAll(permissionService.getAllPermission());
            }
            if(level.equals("2")){
                list.addAll(permissionService.getAllPermissionChild());
            }
            if(level.equals("3")){
                list.addAll(permissionService.getAllPermissionButton());
            }
        }
        HashMap<String,Object> result = new HashMap<>();
        if(list.size()==0){
            result.put("state",404);
        }else{
            result.put("state",200);
        }
        result.put("data",list);
        return JSON.toJSONString(result);
    }

}
