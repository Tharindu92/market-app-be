package com.api.email.Models;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Email {

    private String[] sendTo;
    private String subject;
    private String text;
    private File attachment;

    public Email(String[] sendTo, String subject, String text) {
        this.sendTo = sendTo;
        this.subject = subject;
        this.text = text;
    }
}

