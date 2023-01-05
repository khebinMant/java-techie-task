package com.microservicio.ps.api.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.ps.api.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{
    
}
