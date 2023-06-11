package com.tuna.otomotivbe.services.impl;

import com.tuna.otomotivbe.entities.User;
import com.tuna.otomotivbe.repository.UserRepository;
import com.tuna.otomotivbe.services.ISendVerificationEmail;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class ImplSendVerificationEmail implements ISendVerificationEmail {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    UserRepository userRepository;

    public void mailSender(User user) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("ttahattuna@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Hesap Doğrulama");

            helper.setText("Hesabınızı doğrulamak için lütfen aşağıdaki " +
                    "linke tıklayın: <a href='http://localhost:8080/api/auth/verify?token="
                    + user.getVerificationToken() + "'>Doğrulama Linki</a>", true);

            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println("Mail gönderimi sırasında bir hata oluştu");
        }

    }
}
