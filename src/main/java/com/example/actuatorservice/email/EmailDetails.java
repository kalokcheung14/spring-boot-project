package com.example.actuatorservice.email;

public record EmailDetails(
        String recipient,
        String msgBody,
        String subject,
        String attachment
) {
}
