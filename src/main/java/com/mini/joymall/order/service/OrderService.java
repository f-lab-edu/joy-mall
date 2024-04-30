package com.mini.joymall.order.service;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.repository.CustomerAddressRepository;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.order.dto.request.CreateOrderItemRequest;
import com.mini.joymall.order.dto.request.CreateOrderRequest;
import com.mini.joymall.order.dto.response.CreateOrderResponse;
import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.product.domain.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerAddressRepository addressRepository;
    private final ProductOptionRepository productOptionRepository;

    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequests) {
        CustomerAddress customerAddress = addressRepository.findByCustomerId(createOrderRequests.getCustomerId()).orElseThrow(NoSuchElementException::new);

        List<OrderItem> orderItems = new ArrayList<>();
        for (CreateOrderItemRequest createOrderItems : createOrderRequests.getOrderItems()) {
            Long productOptionId = createOrderItems.getProductOptionId();
            Integer selectedQuantity = createOrderItems.getSelectedQuantity();
            Integer price = createOrderItems.getPrice();

            ProductOption productOption = productOptionRepository.findById(productOptionId).orElseThrow(NoSuchElementException::new);
            productOption.decreaseStock(selectedQuantity);
            productOptionRepository.save(productOption);

            OrderItem orderItem = new OrderItem(productOption.getProductId(), productOptionId, selectedQuantity, price);
            orderItems.add(orderItem);
        }
        Order order = Order.createOrder(customerAddress.getId(), createOrderRequests.getCustomerId(), orderItems);
        Order savedOrder = orderRepository.save(order);
        return CreateOrderResponse.from(savedOrder, customerAddress);
    }
}