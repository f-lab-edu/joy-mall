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
import com.mini.joymall.sale.domain.entity.SalesGroup;
import com.mini.joymall.sale.domain.entity.SalesProduct;
import com.mini.joymall.sale.domain.entity.SalesStatus;
import com.mini.joymall.sale.domain.repository.SalesGroupRepository;
import com.mini.joymall.sale.domain.repository.SalesProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private SalesProductRepository salesProductRepository;

    @Autowired
    private SalesGroupRepository salesGroupRepository;

    @Test
    void 여러개의_주문_아이템을_담고_주문_성공() {
        // given
        Customer customer = new Customer("test123@test.com", "1234", "test", "010-1235-5321");
        Long customerId = customerRepository.save(customer)
                .getId();

        Location location = new Location("대한민국", "서울", "강남구", "도산대로", "010-123", "상세주소");
        CustomerAddress customerAddress = new CustomerAddress(customerId, "test", "010-1234-4321", location);
        customerAddressRepository.save(customerAddress);

        ProductOption productOption1 = new ProductOption(1L, "딸기맛");
        ProductOption productOption2 = new ProductOption(1L, "초코맛");
        ProductOption savedProductOption1 = productOptionRepository.save(productOption1);
        ProductOption savedProductOption2 = productOptionRepository.save(productOption2);


        SalesProduct salesProduct1 = new SalesProduct(savedProductOption1.getId(), 1000, 50, SalesStatus.ON_SALES);
        SalesProduct salesProduct2 = new SalesProduct(savedProductOption2.getId(), 2000, 50, SalesStatus.ON_SALES);

        Set<SalesProduct> salesProducts = new LinkedHashSet<>();
        salesProducts.add(salesProduct1);
        salesProducts.add(salesProduct2);
        SalesGroup salesGroup = new SalesGroup(salesProducts);
        SalesGroup savedSalesGroup = salesGroupRepository.save(salesGroup);
        List<SalesProduct> savedSalesProducts = savedSalesGroup.getSalesProducts().stream().toList();

        CreateOrderItemRequest createOrderItemRequest1 = new CreateOrderItemRequest(savedSalesProducts.get(0).getId(), 5, 1000);
        CreateOrderItemRequest createOrderItemRequest2 = new CreateOrderItemRequest(savedSalesProducts.get(1).getId(), 3, 2000);
        List<CreateOrderItemRequest> createOrderItemRequests = Arrays.asList(createOrderItemRequest1, createOrderItemRequest2);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(customerId, createOrderItemRequests);

        // when
        CreateOrderResponse savedOrder = orderService.createOrder(createOrderRequest);
        Order findOrder = orderRepository.findById(savedOrder.getId())
                .orElseThrow(NoSuchElementException::new);
        List<OrderItem> orderItems = findOrder.getOrderItems().stream().toList();
        List<OrderHistory> orderHistories = orderHistoryRepository.findByOrderId(findOrder.getId())
                .orElseThrow(NoSuchElementException::new)
                .stream().toList();

        // then
        assertThat(orderHistories.get(0).getOrderStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(orderItems.size()).isEqualTo(2);
        assertThat(orderItemRepository.findById(orderItems.get(0).getId()).isPresent()).isTrue();
        assertThat(orderItemRepository.findById(orderItems.get(1).getId()).isPresent()).isTrue();

        assertThat(salesProductRepository.findById(savedSalesProducts.get(0).getId())
                .orElseThrow(NoSuchElementException::new)
                .getSalesStock()).isEqualTo(45);

        assertThat(salesProductRepository.findById(savedSalesProducts.get(1).getId())
                .orElseThrow(NoSuchElementException::new)
                .getSalesStock()).isEqualTo(47);
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

        ProductOption productOption1 = new ProductOption(1L, "딸기맛");
        ProductOption savedProductOption1 = productOptionRepository.save(productOption1);

        SalesProduct salesProduct1 = new SalesProduct(savedProductOption1.getId(), 1000, 5, SalesStatus.ON_SALES);

        Set<SalesProduct> salesProducts = new HashSet<>();
        salesProducts.add(salesProduct1);
        SalesGroup salesGroup = new SalesGroup(salesProducts);
        SalesGroup savedSalesGroup = salesGroupRepository.save(salesGroup);
        List<SalesProduct> savedSalesProducts = savedSalesGroup.getSalesProducts().stream().toList();

        CreateOrderItemRequest createOrderItemRequest1 = new CreateOrderItemRequest(savedSalesProducts.get(0).getId(), 10, 1000);
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

        ProductOption productOption1 = new ProductOption(1L, "딸기맛");
        ProductOption savedProductOption1 = productOptionRepository.save(productOption1);

        SalesProduct salesProduct1 = new SalesProduct(savedProductOption1.getId(), 1000, 50, SalesStatus.ON_SALES);

        Set<SalesProduct> salesProducts = new HashSet<>();
        salesProducts.add(salesProduct1);
        SalesGroup salesGroup = new SalesGroup(salesProducts);
        SalesGroup savedSalesGroup = salesGroupRepository.save(salesGroup);
        List<SalesProduct> savedSalesProducts = savedSalesGroup.getSalesProducts().stream().toList();

        CreateOrderItemRequest createOrderItemRequest1 = new CreateOrderItemRequest(savedSalesProducts.get(0).getId(), 10, 1000);
        List<CreateOrderItemRequest> createOrderItemRequests = List.of(createOrderItemRequest1);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(customerId, createOrderItemRequests);

        // when
        CreateOrderResponse order = orderService.createOrder(createOrderRequest);
        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(NoSuchElementException::new);
        List<OrderHistory> orderHistories = orderHistoryRepository.findByOrderId(savedOrder.getId())
                .orElseThrow(NoSuchElementException::new)
                .stream().toList();

        // then
        assertThat(orderHistories.get(0).getOrderStatus()).isEqualTo(OrderStatus.PENDING);
    }

    @Test
    void 동일한_상품_옵션을_여러개_주문한다() {
        // given
        Customer customer = new Customer("abc12345@test.com", "1234", "test", "010-5555-4444");
        Long customerId = customerRepository.save(customer)
                .getId();

        Location location = new Location("대한민국", "서울", "강남구", "도산대로", "010-123", "상세주소");
        CustomerAddress customerAddress = new CustomerAddress(customerId, "test", "010-1234-4321", location);
        customerAddressRepository.save(customerAddress);

        ProductOption productOption1 = new ProductOption(1L, "딸기맛");
        ProductOption savedProductOption1 = productOptionRepository.save(productOption1);

        SalesProduct salesProduct1 = new SalesProduct(savedProductOption1.getId(), 1000, 50, SalesStatus.ON_SALES);
        Set<SalesProduct> salesProducts = new HashSet<>();
        salesProducts.add(salesProduct1);
        SalesGroup salesGroup = new SalesGroup(salesProducts);
        SalesGroup savedSalesGroup = salesGroupRepository.save(salesGroup);
        List<SalesProduct> savedSalesProducts = savedSalesGroup.getSalesProducts().stream().toList();

        Long savedSalesProductId = savedSalesProducts.get(0).getId();

        CreateOrderItemRequest createOrderItemRequest1 = new CreateOrderItemRequest(savedSalesProductId, 10, 1000);
        CreateOrderItemRequest createOrderItemRequest2 = new CreateOrderItemRequest(savedSalesProductId, 10, 1000);
        CreateOrderItemRequest createOrderItemRequest3 = new CreateOrderItemRequest(savedSalesProductId, 10, 1000);
        List<CreateOrderItemRequest> createOrderItemRequests = List.of(createOrderItemRequest1, createOrderItemRequest2, createOrderItemRequest3);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(customerId, createOrderItemRequests);

        // when
        orderService.createOrder(createOrderRequest);
        SalesProduct findSalesProduct = salesProductRepository.findById(savedSalesProductId).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(findSalesProduct.getSalesStock()).isEqualTo(20);
    }
}