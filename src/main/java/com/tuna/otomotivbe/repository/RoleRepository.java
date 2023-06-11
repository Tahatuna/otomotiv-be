package com.tuna.otomotivbe.repository;

import com.tuna.otomotivbe.Entites.DbERole;
import com.tuna.otomotivbe.Entites.DbRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DbRoleRepository extends JpaRepository<DbRole, Long> {
    Optional<DbRole> findByName(DbERole name);
}
