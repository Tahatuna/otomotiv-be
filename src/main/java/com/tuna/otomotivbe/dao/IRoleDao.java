package com.tuna.otomotivbe.dao;

import com.tuna.otomotivbe.entities.ERole;
import com.tuna.otomotivbe.entities.Role;

public interface IRoleDao {

    Role getRoleByName(ERole name);

}
