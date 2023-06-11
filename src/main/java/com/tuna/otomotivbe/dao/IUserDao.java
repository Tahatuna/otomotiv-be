package com.tuna.otomotivbe.dao;

import com.tuna.otomotivbe.entities.User;

import java.util.Optional;

public interface IAuthDao {

   Optional<User> getByUserName(String userName);
   User save(User user);

}
