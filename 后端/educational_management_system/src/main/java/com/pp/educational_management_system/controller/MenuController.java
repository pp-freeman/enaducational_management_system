package com.pp.educational_management_system.controller;

import com.alibaba.fastjson.JSON;
import com.pp.educational_management_system.domain.Menu;
import com.pp.educational_management_system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MenuController {

    @Autowired
    IMenuService menuService;

    @RequestMapping("/menus")
    public String getAllMenus(){
        HashMap<String,Object> data = new HashMap<>();
        int status = 404; //错误为404，成功为200
        List<Menu> list = menuService.findMenu("001");
        if (list.size()!=0){
            data.put("menus",list);
            data.put("flag",200);
        }else {
            data.put("flag",404);
        }
        String s = JSON.toJSONString(data);
        return s;
    }
}
