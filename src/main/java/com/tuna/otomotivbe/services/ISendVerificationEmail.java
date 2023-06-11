package com.tuna.otomotivbe.services;

import com.tuna.otomotivbe.entities.User;

public interface ISendVerificationEmail {
     void mailSender(User user);

}
