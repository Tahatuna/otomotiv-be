package com.tuna.otomotivbe.dao;

import com.tuna.otomotivbe.entities.User;

import java.util.Optional;

public interface IUserDao {

    Optional<User> getByUserName(String userName);

    User save(User user);

    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);

    User getUserByVerificationToken(String verificationToken);

    Optional<User> getById(Long id);

}
