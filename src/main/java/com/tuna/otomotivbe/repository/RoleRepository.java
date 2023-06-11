package com.tuna.otomotivbe.repository;

import com.tuna.otomotivbe.entities.ERole;
import com.tuna.otomotivbe.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
