package com.mini.joymall.order.service;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.domain.entity.OrderStatus;
import com.mini.joymall.order.dto.request.CreateOrderItemRequest;
import com.mini.joymall.product.service.ProductOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderCreator {

    private final ProductOptionService productOptionService;

    public Order create(List<CreateOrderItemRequest> createOrderItemRequests, CustomerAddress customerAddress) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (CreateOrderItemRequest createOrderItemRequest : createOrderItemRequests) {
            productOptionService.decreaseStock(createOrderItemRequest.getProductOptionId(), createOrderItemRequest.getSelectedQuantity());
            orderItems.add(createOrderItemRequest.toEntity());
        }
        return new Order(OrderStatus.PENDING, orderItems, customerAddress.getId(), customerAddress.getCustomerId());
    }
}
