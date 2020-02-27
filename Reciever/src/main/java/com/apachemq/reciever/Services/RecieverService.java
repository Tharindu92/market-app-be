package com.apachemq.reciever.Services;

import com.apachemq.reciever.Models.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableJms
public class RecieverService {
    private final Logger logger = LoggerFactory.getLogger(RecieverService.class);

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Order order;

    @Value("${paypal.pay}")
    private String paypaluri;

    @JmsListener(destination = "market-in-queue")
    public void listener(String message) throws JsonProcessingException {
        order = mapper.readValue(message, Order.class);
        logger.info("Message received {} ", order);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(paypaluri, order, String.class);
        logger.info("Result payment {} ", result);
    }
}
