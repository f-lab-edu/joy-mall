package com.mini.joymall.order.service;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.repository.CustomerAddressRepository;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.order.dto.request.CreateOrderRequest;
import com.mini.joymall.order.dto.response.CreateOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerAddressRepository addressRepository;
    private final OrderCreator orderCreator;

    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequests) {
        CustomerAddress customerAddress = addressRepository.findByCustomerId(createOrderRequests.getCustomerId()).orElseThrow(NoSuchElementException::new);
        Order order = orderCreator.create(createOrderRequests.getOrderItems(), customerAddress);
        Order savedOrder = orderRepository.save(order);
        return CreateOrderResponse.from(savedOrder, customerAddress);
    }
}