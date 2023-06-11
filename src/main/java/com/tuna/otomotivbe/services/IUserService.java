package com.tuna.otomotivbe.services;

import com.tuna.otomotivbe.entities.User;

import java.util.Optional;

public interface IUserService {

    void logFailedLoginAttempt(String userName);

    Optional<User> getByUserName(String userName);

    User save(User user);

    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);

    boolean verifyMail(String verificationToken);

}
