package com.example.audit.service;

import com.example.audit.annotation.Trackable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Trackable
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }
}

