package com.mini.joymall.sale.service;

import com.google.gson.Gson;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.dto.OrderItemDTO;
import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component("salesProductFacadeKafka")
@RequiredArgsConstructor
public class SalesProductFacadeKafka implements SalesProductFacade {

//    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "new-stock-decrease";
    private final Gson gson = new Gson();

    @Override
    public void decreaseStock(Set<OrderItem> orderItems) {
        Set<OrderItemDTO> items = orderItems.stream().map(OrderItemDTO::from).collect(Collectors.toSet());
        String json = gson.toJson(items);
//        kafkaTemplate.send(TOPIC, json);
    }
}
