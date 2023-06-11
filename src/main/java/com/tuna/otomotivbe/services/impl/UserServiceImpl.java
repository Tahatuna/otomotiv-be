package com.tuna.otomotivbe.services.impl;

import com.tuna.otomotivbe.controllers.AuthController;
import com.tuna.otomotivbe.dao.IAuthDao;
import com.tuna.otomotivbe.entities.User;
import com.tuna.otomotivbe.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
@Service
public class AuthServiceImpl implements IAuthService {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    @Autowired
    IAuthDao authDao;

    @Override
    public void logFailedLoginAttempt(String username) {

        LOGGER.error("Failed login attempt for user: {}", username);

        User user = authDao.getByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        user.setLoginAttempts(user.getLoginAttempts() + 1);

        // 5 hatalı giriş yapıldıysa, kullanıcıyı bloke et
        if (user.getLoginAttempts() >= 5) {
            user.setBlocked(true);
        }
        authDao.save(user);
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        authDao.getByUserName(userName);
        return null;
    }
}
