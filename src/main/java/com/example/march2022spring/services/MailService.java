package com.example.march2022spring.services;

import com.example.march2022spring.models.User;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailSender;

    public void sendMailForActivate(User user, File file) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        try {
            helper.setFrom("testforjava2022@gmail.com");
            helper.setTo(user.getEmail());
            helper.setText("to activate visit this <a href='http://localhost:8080/users/activate?id=" + user.getId() + "'>link</a>", true);
            helper.addAttachment(user.getPhoto(), file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
