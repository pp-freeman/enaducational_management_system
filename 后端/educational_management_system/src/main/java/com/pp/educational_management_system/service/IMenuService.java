package com.pp.educational_management_system.service;

import com.pp.educational_management_system.domain.Menu;

import java.util.List;

public interface IMenuService {
    public List<Menu> findMenu(String id);
}
