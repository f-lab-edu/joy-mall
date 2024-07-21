package com.mini.joymall.order.service;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.repository.CustomerAddressRepository;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.order.dto.request.CreateOrderRequest;
import com.mini.joymall.order.dto.response.CreateOrderResponse;
import com.mini.joymall.sale.service.SalesProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class OrderService {
    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;
    private final CustomerAddressRepository addressRepository;
    private final SalesProductFacade salesProductFacade;

    @Autowired
    public OrderService(OrderValidator orderValidator,
                        OrderRepository orderRepository,
                        CustomerAddressRepository addressRepository,
                        @Qualifier("salesProductFacadeRedis") SalesProductFacade salesProductFacade) {
        this.orderValidator = orderValidator;
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
        this.salesProductFacade = salesProductFacade;
    }

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