package com.pp.educational_management_system.dao;

import com.pp.educational_management_system.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuDao {
    public List<Menu> findMenus(String id);
}
