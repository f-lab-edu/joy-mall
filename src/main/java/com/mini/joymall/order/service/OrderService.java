package com.mini.joymall.order.service;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.repository.CustomerAddressRepository;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.order.dto.request.CreateOrderRequest;
import com.mini.joymall.order.dto.response.CreateOrderResponse;
import com.mini.joymall.sale.service.SalesProductFacade;
import com.mini.joymall.sale.service.SalesProductFacadeImpl;
import com.mini.joymall.sale.service.SalesProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;
    private final SalesProductFacade salesProductFacade;
    private final CustomerAddressRepository addressRepository;

    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequests) {
        Set<OrderItem> orderItems = createOrderRequests.toOrderItems();
        orderValidator.validate(orderItems);

        CustomerAddress customerAddress = addressRepository.findByCustomerId(createOrderRequests.getCustomerId())
                .orElseThrow(NoSuchElementException::new);

        salesProductFacade.decreaseStock(orderItems);
        Order savedOrder = orderRepository.save(Order.ordered(customerAddress.getId(), orderItems));
        return CreateOrderResponse.from(savedOrder, customerAddress);
    }
}