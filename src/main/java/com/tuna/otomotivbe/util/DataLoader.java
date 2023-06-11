package com.tuna.otomotivbe.util;

import com.tuna.otomotivbe.entities.ERole;
import com.tuna.otomotivbe.entities.Role;
import com.tuna.otomotivbe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addRoleIfNotExists(ERole.ROLE_USER);
        addRoleIfNotExists(ERole.ROLE_ADMIN);
    }

    private void addRoleIfNotExists(ERole roleName) {
        Role role = new Role(roleName);
        roleRepository.save(role);
    }


}
