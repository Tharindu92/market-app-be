package com.kafka.consumer.Services;

import com.kafka.consumer.Models.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KafkaConsumerService {

    @Value("${market.api.email}")
    private String EMAILAPIURL;

//    @KafkaListener(topics = "${kafka.topic}", groupId = "group_id")
//    public void consume(String message) {
//        System.out.println("Consumed message: " + message);
//    }


    @KafkaListener(topics = "${kafka.topic}", groupId = "group_json",
            containerFactory = "emailKafkaListenerFactory")
    public void consumeJson(Email email) {
        System.out.println("Consumed JSON Message: " + email);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(EMAILAPIURL, email, String.class);
    }
}
