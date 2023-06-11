package com.tuna.otomotivbe.repository;

import com.tuna.otomotivbe.Entites.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DbUserRepository extends JpaRepository<DbUser, Long> {
    Optional<DbUser> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
