package com.tuna.otomotivbe.services.impl;

import com.tuna.otomotivbe.controllers.AuthController;
import com.tuna.otomotivbe.dao.IUserDao;
import com.tuna.otomotivbe.entities.User;
import com.tuna.otomotivbe.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    @Autowired
    private IUserDao userDao;

    @Override
    public void logFailedLoginAttempt(String userName) {

        LOGGER.error("Failed login attempt for user: {}", userName);

        User user = userDao.getByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
        user.setLoginAttempts(user.getLoginAttempts() + 1);

        if (user.getLoginAttempts() >= 5) {
            user.setBlocked(true);
        }
        userDao.save(user);
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        return userDao.getByUserName(userName);
    }

    @Override
    public boolean existsByUsername(String userName) {
        return userDao.existsByUsername(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean verifyMail(String verificationToken) {

        User user = userDao.getUserByVerificationToken(verificationToken);
        if (user != null) {
            user.setEmailVerified(true);
            userDao.save(user);
            return true;
        }
        return false;
    }

}

