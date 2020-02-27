package com.kafka.producer.Services;

import com.kafka.producer.Models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Autowired
    private KafkaTemplate<String,Email> kafkaTemplate;

    public String kafkaSend(Email email){
        kafkaTemplate.send(kafkaTopic, email);

        return "SUCCESS";
    }
}
