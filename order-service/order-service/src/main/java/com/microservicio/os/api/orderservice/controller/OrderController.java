package com.microservicio.os.api.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.os.api.orderservice.entity.Order;
import com.microservicio.os.api.orderservice.model.Payment;
import com.microservicio.os.api.orderservice.model.TransactionRequest;
import com.microservicio.os.api.orderservice.model.TransactionResponse;
import com.microservicio.os.api.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderService service;
    
    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
        return service.saveOrder(request);
    }
}
