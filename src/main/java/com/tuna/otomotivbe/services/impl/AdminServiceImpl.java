package com.tuna.otomotivbe.services.impl;

import com.tuna.otomotivbe.dao.IUserDao;
import com.tuna.otomotivbe.entities.User;
import com.tuna.otomotivbe.exceptionhandling.ResourceNotFoundException;
import com.tuna.otomotivbe.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IUserDao userDao;

    @Override
    public void unblockUser(Long id) {
        User user = userDao.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setBlocked(false);
        user.setLoginAttempts(0);
        userDao.save(user);
    }

}
