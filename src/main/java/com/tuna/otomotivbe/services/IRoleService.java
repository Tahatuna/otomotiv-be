package com.tuna.otomotivbe.services;

import com.tuna.otomotivbe.entities.ERole;
import com.tuna.otomotivbe.entities.Role;

public interface IRoleService {
    Role getRoleByName(ERole name);

}
