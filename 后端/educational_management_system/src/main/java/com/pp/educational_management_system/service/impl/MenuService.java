package com.pp.educational_management_system.service.impl;

import com.pp.educational_management_system.dao.IMenuDao;
import com.pp.educational_management_system.domain.Menu;
import com.pp.educational_management_system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements IMenuService {

    @Autowired
    IMenuDao menuDao;

    @Override
    public List<Menu> findMenu(String id) {
        return menuDao.findMenus(id);
    }
}
