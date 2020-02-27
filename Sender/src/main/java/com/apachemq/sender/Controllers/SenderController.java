package com.apachemq.sender.Controllers;

import com.apachemq.sender.Models.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class SenderController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${activemq.queue}")
    private String queue;

    @CrossOrigin
    @PostMapping(value = "/send")
    public ResponseEntity<String> publish(@RequestBody Order order) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        jmsTemplate.convertAndSend(queue, mapper.writeValueAsString(order));
        return new ResponseEntity(order, HttpStatus.OK);
    }
}
