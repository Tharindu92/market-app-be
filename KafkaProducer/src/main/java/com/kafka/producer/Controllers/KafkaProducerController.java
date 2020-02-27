package com.kafka.producer.Controllers;

import com.kafka.producer.Models.Email;
import com.kafka.producer.Services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.cert.CertificateException;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/publish")
    public String createPayment(@RequestBody Email email) {
        return kafkaProducerService.kafkaSend(email);
    }
}
