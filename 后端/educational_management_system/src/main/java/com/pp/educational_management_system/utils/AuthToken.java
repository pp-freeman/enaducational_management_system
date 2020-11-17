package com.pp.educational_management_system.utils;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.context.annotation.Configuration;


public class AuthToken extends UsernamePasswordToken {
    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
