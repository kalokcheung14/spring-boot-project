package com.example.actuatorservice.email;

import com.example.actuatorservice.GenericResponse;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public GenericResponse sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.recipient());
            mailMessage.setText(details.msgBody());
            mailMessage.setSubject(details.subject());

            javaMailSender.send(mailMessage);
            return new GenericResponse("Mail sent successfully");
        } catch (Exception e) {
            return new GenericResponse("Error while sending mail");
        }
    }

    @Override
    public GenericResponse sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.recipient());
            mimeMessageHelper.setText(details.msgBody());
            mimeMessageHelper.setSubject(details.subject());

            FileSystemResource file = new FileSystemResource(new File(details.attachment()));

            mimeMessageHelper.addAttachment(file.getFilename(), file);
            javaMailSender.send(mimeMailMessage);
            return new GenericResponse("Mail sent successfully");
        } catch (Exception e) {
            return new GenericResponse("Error while sending mail");
        }
    }
}
