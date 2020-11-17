package com.pp.educational_management_system.utils;

import com.pp.educational_management_system.domain.Menu;
import com.pp.educational_management_system.domain.SubMenu;
import com.pp.educational_management_system.domain.SysToken;
import com.pp.educational_management_system.domain.User;
import com.pp.educational_management_system.service.IMenuService;
import com.pp.educational_management_system.service.impl.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthRealm  extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private IMenuService menuService;

    @Override
    /**
     * 授权 获取用户的角色和权限
     *@param  [principals]
     *@return org.apache.shiro.authz.AuthorizationInfo
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //1. 从 PrincipalCollection 中来获取登录用户的信息
        User user = (User) principals.getPrimaryPrincipal();

        List<Menu> list = menuService.findMenu("001");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for(Menu m : list){
            System.out.println(m.getPath());
            simpleAuthorizationInfo.addStringPermission(m.getPath());
            for(SubMenu sm : m.getMenuList()){
                System.out.println(sm.getPath());
                simpleAuthorizationInfo.addStringPermission(sm.getPath());
            }
        }

        return simpleAuthorizationInfo;
    }

    @Override
    /**
     * 认证 判断token的有效性
     *@param  [token]
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token，既前端传入的token
        String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = shiroService.findByToken(accessToken);
        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        User user = shiroService.findByUserId(tokenEntity.getUserId());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        return info;
    }

}
