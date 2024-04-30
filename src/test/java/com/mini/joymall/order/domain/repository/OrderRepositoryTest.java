package com.mini.joymall.order.domain.repository;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.entity.Customer;
import com.mini.joymall.customer.domain.entity.Location;
import com.mini.joymall.customer.domain.repository.CustomerAddressRepository;
import com.mini.joymall.customer.domain.repository.CustomerRepository;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.domain.entity.OrderStatus;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.product.domain.repository.ProductOptionRepository;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.seller.domain.entity.Seller;
import com.mini.joymall.seller.domain.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductOptionRepository productOptionRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerAddressRepository addressRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    /**
     * 주문 프로세스
     * - 판매자, 상품 생성
     * - 고객, 주소 생성
     * - 주문 아이템, 주문과 함께 저장
     * - 결제, 배송지 생성
     */
    @Test
    void 주문_테스트() {
        // given
        Seller sellerA = new Seller("abc@a.com", "1234", "ASeller", "AStore", "010");
        Seller savedSellerA = sellerRepository.save(sellerA);

        Product productA = new Product(savedSellerA.getId(), "아이폰 실리콘 케이스", "아이폰 실리콘 케이스 설명", "아이폰 실리콘 케이스 경로");
        Product productB = new Product(savedSellerA.getId(), "아이폰 가죽 케이스", "아이폰 가죽 케이스 설명", "아이폰 가죽 케이스 경로");
        Product savedProductA = productRepository.save(productA);
        Product savedProductB = productRepository.save(productB);

        ProductOption productOptionA1 = new ProductOption(savedProductA.getId(), "아이폰 15 Pro Max", 21000, 100);
        ProductOption productOptionA2 = new ProductOption(savedProductA.getId(), "아이폰 15 Pro", 21000, 100);
        ProductOption productOptionB1 = new ProductOption(savedProductB.getId(), "아이폰 13", 33000, 10);
        ProductOption savedProductOptionA1 = productOptionRepository.save(productOptionA1);
        ProductOption savedProductOptionA2 = productOptionRepository.save(productOptionA2);
        ProductOption savedProductOptionB1 = productOptionRepository.save(productOptionB1);

        Customer customer = new Customer("customer@a.com", "1234", "customerA", "010");
        Customer savedCustomerA = customerRepository.save(customer);
        Location location = new Location("대한민국", "서울시", "강남구", "도산대로", "001-001", "힐스테이트");
        CustomerAddress addressA = new CustomerAddress(savedCustomerA.getId(), "나의 집", "010-1234-1234", location);
        CustomerAddress savedAddressA = addressRepository.save(addressA);

        OrderItem orderItemA1 = new OrderItem(savedProductOptionA1.getProductId(), savedProductOptionA1.getId(), 3, savedProductOptionA1.getPrice());
        OrderItem orderItemA2 = new OrderItem(savedProductOptionA2.getProductId(), savedProductOptionA2.getId(), 5, savedProductOptionA2.getPrice());
        OrderItem orderItemB1 = new OrderItem(savedProductOptionB1.getProductId(), savedProductOptionB1.getId(), 8, savedProductOptionB1.getPrice());

        Order order = new Order(OrderStatus.PENDING, savedAddressA.getId(), savedCustomerA.getId());
        order.addOrderItem(orderItemA1);
        order.addOrderItem(orderItemA2);
        order.addOrderItem(orderItemB1);
        orderRepository.save(order);

        // when
        List<Order> orders = orderRepository.findByCustomerId(savedCustomerA.getId());

        // then
        assertThat(orders.get(0).getOrderItems()).contains(orderItemA1, orderItemA2, orderItemB1);
        assertThat(orders.size()).isEqualTo(1);
        assertThat(orders.get(0).getOrderItems()).hasSize(3);
    }
}