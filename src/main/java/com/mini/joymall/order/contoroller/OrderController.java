package com.mini.joymall.order.contoroller;

import com.mini.joymall.order.dto.request.CreateOrderRequest;
import com.mini.joymall.order.dto.response.CreateOrderResponse;
import com.mini.joymall.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(createOrderRequest));
    }
}
