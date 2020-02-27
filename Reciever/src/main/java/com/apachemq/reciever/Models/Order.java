package com.apachemq.reciever.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Order {

    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;

}
