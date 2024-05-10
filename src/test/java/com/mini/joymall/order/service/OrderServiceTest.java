package com.mini.joymall.order.service;

import com.mini.joymall.customer.domain.entity.Customer;
import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.entity.Location;
import com.mini.joymall.customer.domain.repository.CustomerAddressRepository;
import com.mini.joymall.customer.domain.repository.CustomerRepository;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderHistory;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.domain.entity.OrderStatus;
import com.mini.joymall.order.domain.repository.OrderHistoryRepository;
import com.mini.joymall.order.domain.repository.OrderItemRepository;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.order.dto.request.CreateOrderItemRequest;
import com.mini.joymall.order.dto.request.CreateOrderRequest;
import com.mini.joymall.order.dto.response.CreateOrderResponse;
import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.product.domain.repository.ProductOptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void 여러개의_주문_아이템을_담고_주문_성공() {
        // given
        Customer customer = new Customer("test123@test.com", "1234", "test", "010-1235-5321");
        Long customerId = customerRepository.save(customer)
                .getId();

        Location location = new Location("대한민국", "서울", "강남구", "도산대로", "010-123", "상세주소");
        CustomerAddress customerAddress = new CustomerAddress(customerId, "test", "010-1234-4321", location);
        customerAddressRepository.save(customerAddress);

        ProductOption productOption1 = new ProductOption(1L, "딸기맛", 1000, 5);
        ProductOption productOption2 = new ProductOption(1L, "초코맛", 1000, 3);
        ProductOption savedProductOption1 = productOptionRepository.save(productOption1);
        ProductOption savedProductOption2 = productOptionRepository.save(productOption2);

        CreateOrderItemRequest createOrderItemRequest1 = new CreateOrderItemRequest(savedProductOption1.getId(), savedProductOption1.getProductId(), 5, 1000);
        CreateOrderItemRequest createOrderItemRequest2 = new CreateOrderItemRequest(savedProductOption2.getId(), savedProductOption2.getProductId(), 3, 1000);
        List<CreateOrderItemRequest> createOrderItemRequests = Arrays.asList(createOrderItemRequest1, createOrderItemRequest2);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(customerId, createOrderItemRequests);

        // when
        CreateOrderResponse savedOrder = orderService.createOrder(createOrderRequest);
        List<OrderItem> orderItems = savedOrder.getOrderItems().stream().toList();

        // then
        assertThat(savedOrder.getStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(orderItems.size()).isEqualTo(2);
        assertThat(orderItemRepository.findById(orderItems.get(0).getId()).isPresent()).isTrue();
        assertThat(orderItemRepository.findById(orderItems.get(1).getId()).isPresent()).isTrue();
        assertThat(productOptionRepository.findById(orderItems.get(0).getProductOptionId()).isPresent()).isTrue();
        assertThat(productOptionRepository.findById(orderItems.get(1).getProductOptionId()).isPresent()).isTrue();
    }

    @Test
    void 주문_수량이_재고를_넘으면_주문_실패() {
        // given
        Customer customer = new Customer("test@test.com", "1234", "test", "010-1234-4321");
        Long customerId = customerRepository.save(customer)
                .getId();

        Location location = new Location("대한민국", "서울", "강남구", "도산대로", "010-123", "상세주소");
        CustomerAddress customerAddress = new CustomerAddress(customerId, "test", "010-1234-4321", location);
        customerAddressRepository.save(customerAddress);

        ProductOption productOption1 = new ProductOption(1L, "딸기맛", 1000, 5);
        ProductOption savedProductOption1 = productOptionRepository.save(productOption1);

        CreateOrderItemRequest createOrderItemRequest1 = new CreateOrderItemRequest(savedProductOption1.getId(), savedProductOption1.getProductId(), 10, 1000);
        List<CreateOrderItemRequest> createOrderItemRequests = List.of(createOrderItemRequest1);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(customerId, createOrderItemRequests);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> orderService.createOrder(createOrderRequest));
    }

    @Test
    void 주문_생성시_주문_히스토리가_저장된다() {
        // given
        Customer customer = new Customer("test54321@test.com", "1234", "test", "010-4444-4321");
        Long customerId = customerRepository.save(customer)
                .getId();

        Location location = new Location("대한민국", "서울", "강남구", "도산대로", "010-123", "상세주소");
        CustomerAddress customerAddress = new CustomerAddress(customerId, "test", "010-1234-4321", location);
        customerAddressRepository.save(customerAddress);

        ProductOption productOption1 = new ProductOption(1L, "딸기맛", 1000, 100);
        ProductOption savedProductOption1 = productOptionRepository.save(productOption1);

        CreateOrderItemRequest createOrderItemRequest1 = new CreateOrderItemRequest(savedProductOption1.getId(), savedProductOption1.getProductId(), 10, 1000);
        List<CreateOrderItemRequest> createOrderItemRequests = List.of(createOrderItemRequest1);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(customerId, createOrderItemRequests);

        // when
        CreateOrderResponse order = orderService.createOrder(createOrderRequest);
        OrderHistory orderHistory = orderHistoryRepository.findByOrderId(order.getId())
                .orElseThrow(NoSuchElementException::new);

        // then
        assertThat(orderHistory.getOrderId()).isEqualTo(order.getId());
        assertThat(orderHistory.getStatus()).isEqualTo(OrderStatus.PENDING);
    }

    @Test
    void 동일한_상품_옵션을_여러개_주문한다() {
        // given
        Customer customer = new Customer("test54321@test.com", "1234", "test", "010-4444-4321");
        Long customerId = customerRepository.save(customer)
                .getId();

        Location location = new Location("대한민국", "서울", "강남구", "도산대로", "010-123", "상세주소");
        CustomerAddress customerAddress = new CustomerAddress(customerId, "test", "010-1234-4321", location);
        customerAddressRepository.save(customerAddress);

        ProductOption productOption1 = new ProductOption(1L, "딸기맛", 1000, 100);
        ProductOption savedProductOption1 = productOptionRepository.save(productOption1);

        Long productOptionId = savedProductOption1.getId();
        Long savedProductOptionProductId = savedProductOption1.getProductId();
        CreateOrderItemRequest createOrderItemRequest1 = new CreateOrderItemRequest(productOptionId, savedProductOptionProductId, 10, 1000);
        CreateOrderItemRequest createOrderItemRequest2 = new CreateOrderItemRequest(productOptionId, savedProductOptionProductId, 10, 1000);
        CreateOrderItemRequest createOrderItemRequest3 = new CreateOrderItemRequest(productOptionId, savedProductOptionProductId, 10, 1000);
        List<CreateOrderItemRequest> createOrderItemRequests = List.of(createOrderItemRequest1, createOrderItemRequest2, createOrderItemRequest3);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(customerId, createOrderItemRequests);

        // when
        orderService.createOrder(createOrderRequest);
        ProductOption findProductOption = productOptionRepository.findById(productOptionId).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(findProductOption.getStockQuantity()).isEqualTo(70);
    }
}