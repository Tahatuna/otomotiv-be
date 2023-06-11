package com.tuna.otomotivbe.dao.impl;

import com.tuna.otomotivbe.dao.IAuthDao;
import com.tuna.otomotivbe.entities.User;
import com.tuna.otomotivbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthDaoImpl implements IAuthDao {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}
