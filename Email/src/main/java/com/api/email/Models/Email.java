package com.api.email.Models;

import lombok.*;
import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Email {

    private String[] sendTo;
    private String subject;
    private String text;
    private File attachment;
    private boolean attached;
}

