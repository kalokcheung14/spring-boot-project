package com.example.actuatorservice.email;

import com.example.actuatorservice.GenericResponse;

public interface EmailService {
    GenericResponse sendSimpleMail(EmailDetails details);
    GenericResponse sendMailWithAttachment(EmailDetails details);
}
