package com.mini.joymall.order.service;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.repository.CustomerAddressRepository;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderHistory;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.domain.entity.OrderStatus;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.order.dto.request.CreateOrderRequest;
import com.mini.joymall.order.dto.response.CreateOrderResponse;
import com.mini.joymall.sale.service.SalesProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final CustomerAddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final SalesProductService salesProductService;
    private final OrderHistoryService orderHistoryService;

    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequests) {
        Set<OrderItem> orderItems = createOrderRequests.toOrderItems();

        CustomerAddress customerAddress = addressRepository.findByCustomerId(createOrderRequests.getCustomerId())
                .orElseThrow(NoSuchElementException::new);
        Order savedOrder = orderRepository.save(Order.ordered(customerAddress.getId(), orderItems));

        orderHistoryService.createHistory(savedOrder.getId(), OrderStatus.PENDING);

        salesProductService.decreaseStock(orderItems);

        return CreateOrderResponse.from(savedOrder, customerAddress);
    }
}