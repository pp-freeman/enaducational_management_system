package com.pp.educational_management_system.controller;

import com.alibaba.fastjson.JSON;
import com.pp.educational_management_system.dao.IUserInfoDao;
import com.pp.educational_management_system.domain.QueryInfo;
import com.pp.educational_management_system.domain.User;
import com.pp.educational_management_system.domain.UserInfo;
import com.pp.educational_management_system.service.IRoleService;
import com.pp.educational_management_system.service.IUserInfoService;
import com.pp.educational_management_system.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserInfoService userInfoService;

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;
//
//    @RequiresPermissions({"/1"})
    @RequestMapping("/userInfoList")
    public String getUserInfoList(@RequestBody QueryInfo queryInfo){
        List<UserInfo> userInfos = userInfoService.getUserInfoList(queryInfo.getPageNum(),queryInfo.getPagesize(),queryInfo.getUsername(),queryInfo.getDepartment(),queryInfo.getNumber());
        HashMap<String,Object> result = new HashMap<String,Object>();
        result.put("number",userInfoService.getUserInfoCount(queryInfo.getUsername(),queryInfo.getDepartment(),queryInfo.getNumber()));
        result.put("userInfos",userInfos);
        String res = JSON.toJSONString(result);
        return res;
    }

    @RequestMapping("/userList")
    public String getUserList(String username,String roleName,int pageNum,int pageSize){
        int number = userService.getAllUserCount(username,roleName);
        List<User> users = userService.getAllUser(username,roleName,(pageNum-1)*pageSize,pageSize);
        for(int i=0;i<users.size();i++){
            if(users.get(i).getRoles().size()!=0) {
                for (int j = 0; j < users.get(i).getRoles().size(); j++) {
                    users.get(i).setRole(users.get(i).getRoles().get(j).getRoleName() + ";");
                }
                users.get(i).setRole(users.get(i).getRole().substring(0, users.get(i).getRole().length() - 1));
            }
        }
        HashMap<String,Object> result = new HashMap<String,Object>();
        result.put("number",number);
        result.put("userInfos",users);
        return JSON.toJSONString(result);
    }


    @RequestMapping("/updateState")
    public String updateUserState(String id,int state){
        if(userService.updateUserState(id,state)>0){
            System.out.println(1);
            return "success";
        }else {
            return "false";
        }

    }

    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){
        user.setId(String.valueOf(UUID.randomUUID()));
        int i = userService.addUser(user.getId(),user.getUsername(),user.getPassword());
        return i>0?"success":"error";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(String id){
        int i= userService.deleteUser(id);
        roleService.deleteUserRole(id);
        return i>0?"success":"error";
    }

    @RequestMapping("/getUpdateUser")
    public String getUpdateUser(String id){
        User user = userService.getUpdateUser(id);
        return JSON.toJSONString(user);
    }

    @RequestMapping("/editUser")
    public String deleteUser(@RequestBody User user){
        int i= userService.editUser(user);
        return i>0?"success":"error";
    }
}
