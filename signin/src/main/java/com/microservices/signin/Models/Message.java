package com.microservices.signin;

import org.springframework.stereotype.Component;

@Component
public class Message {
    public boolean success;

    public Message() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
