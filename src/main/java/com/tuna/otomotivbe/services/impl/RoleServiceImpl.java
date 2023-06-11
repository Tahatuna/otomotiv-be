package com.tuna.otomotivbe.services.impl;

import com.tuna.otomotivbe.dao.IRoleDao;
import com.tuna.otomotivbe.entities.ERole;
import com.tuna.otomotivbe.entities.Role;
import com.tuna.otomotivbe.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public Role getRoleByName(ERole name) {
        return roleDao.getRoleByName(name);
    }
}
