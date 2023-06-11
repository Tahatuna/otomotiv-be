package com.tuna.otomotivbe.dao.impl;

import com.tuna.otomotivbe.dao.IUserDao;
import com.tuna.otomotivbe.entities.User;
import com.tuna.otomotivbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDaoImpl implements IUserDao {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String userName) {
        return userRepository.existsByUsername(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserByVerificationToken(String verificationToken) {
        return userRepository.findByVerificationToken(verificationToken);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}