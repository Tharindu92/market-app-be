package com.kafka.producer.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

//    public Email(String[] sendTo, String subject, String text) {
//        this.sendTo = sendTo;
//        this.subject = subject;
//        this.text = text;
//    }
}

