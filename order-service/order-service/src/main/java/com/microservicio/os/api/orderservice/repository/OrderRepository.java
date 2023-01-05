package com.microservicio.os.api.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicio.os.api.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
