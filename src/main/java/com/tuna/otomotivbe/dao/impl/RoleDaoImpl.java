package com.tuna.otomotivbe.dao.impl;

import com.tuna.otomotivbe.dao.IRoleDao;
import com.tuna.otomotivbe.entities.ERole;
import com.tuna.otomotivbe.entities.Role;
import com.tuna.otomotivbe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDaoImpl implements IRoleDao {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByName(ERole name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }

}
