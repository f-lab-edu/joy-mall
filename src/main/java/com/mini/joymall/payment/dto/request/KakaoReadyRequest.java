package com.mini.joymall.payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.joymall.commons.properties.KakaoPayProperties;
import com.mini.joymall.order.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class KakaoReadyRequest {
    @JsonProperty("cid")
    private String cid;

    @JsonProperty("partner_order_id")
    private String partnerOrderId;

    @JsonProperty("partner_user_id")
    private String partnerUserId;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("total_amount")
    private String totalAmount;

    @JsonProperty("tax_free_amount")
    private String taxFreeAmount;

    @JsonProperty("approval_url")
    private String approvalUrl;

    @JsonProperty("cancel_url")
    private String cancelUrl;

    @JsonProperty("fail_url")
    private String failUrl;

    public static KakaoReadyRequest create(KakaoPayProperties properties, Order order) {
        return KakaoReadyRequest.builder()
                .cid(properties.getCid())
                .partnerOrderId(String.valueOf(order.getId()))
                .partnerUserId(String.valueOf(order.getCustomerAddressId()))
                .itemName("JoyMall")
                .quantity(String.valueOf(Order.getSumQuantity(order.getOrderItems())))
                .totalAmount(String.valueOf(Order.calculateOrderTotalPrice(order.getOrderItems())))
                .taxFreeAmount("0")
                .approvalUrl("http://localhost:8081/kakaoPay/success?partner_order_id=" + order.getId())
                .cancelUrl("http://localhost:8081/kakaoPay/cancel?partner_order_id=" + order.getId())
                .failUrl("http://localhost:8081/kakaoPay/fail?partner_order_id=" + order.getId())
                .build();
    }

    public String convertToJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("KakaoReadyRequest 객체를 Json 변환에 실패하였습니다.", e);
        }
    }
}
