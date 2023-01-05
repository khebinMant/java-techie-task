package com.microservicio.os.api.orderservice.service;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicio.os.api.orderservice.entity.Order;
import com.microservicio.os.api.orderservice.model.Payment;
import com.microservicio.os.api.orderservice.model.TransactionRequest;
import com.microservicio.os.api.orderservice.model.TransactionResponse;
import com.microservicio.os.api.orderservice.repository.OrderRepository;


@Service
public class OrderService {

    Logger logger= LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository repository;

    @Autowired
    // @Lazy
    private RestTemplate template;

    private String response="";


    // @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    // private String ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request) {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //rest call
        Payment paymentResponse = template.postForObject("http://localhost:8989/payment/doPayment", payment, Payment.class);

        response = paymentResponse.getPaymentStatus().equals("success")?"payment proccesing succesfull and order placed":"there is a failure in payment api, order added to cart";

        repository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}
