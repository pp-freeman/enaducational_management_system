package com.pp.educational_management_system.controller;

import com.alibaba.fastjson.JSON;
import com.pp.educational_management_system.domain.User;
import com.pp.educational_management_system.service.impl.ShiroService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class ShiroController {

    private final ShiroService shiroService;

    public ShiroController(ShiroService shiroService) {
        this.shiroService = shiroService;
    }


    /**
     * 登录
     */
    @RequestMapping("/login")
    public String login(@RequestBody User us) {
        Map<String, Object> result = new HashMap<>();
        String username = us.getUsername();
        String password = us.getPassword();
        //用户信息
        User user = shiroService.getUserByName(username);
        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(password)) {
            result.put("status", 400);
            result.put("msg", "账号或密码有误");
        } else {
            //生成token，并保存到数据库
            result = shiroService.createToken(user.getId());
            result.put("status", 200);
            result.put("msg", "登陆成功");
            result.put("username",user.getUsername());
        }
        String res = JSON.toJSONString(result);
        return res;
    }

    /**
     * 退出
     */
    @RequestMapping("/logout")
    public Map<String, Object> logout(@RequestHeader("token")String token) {
        Map<String, Object> result = new HashMap<>();
        shiroService.logout(token);
        result.put("status", 200);
        result.put("msg", "您已安全退出系统");
        return result;
    }
}
