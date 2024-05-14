package com.mini.joymall.order.domain.repository;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.entity.Customer;
import com.mini.joymall.customer.domain.entity.Location;
import com.mini.joymall.customer.domain.repository.CustomerAddressRepository;
import com.mini.joymall.customer.domain.repository.CustomerRepository;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.product.domain.repository.ProductOptionRepository;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.sale.domain.entity.SalesGroup;
import com.mini.joymall.sale.domain.entity.SalesProduct;
import com.mini.joymall.sale.domain.entity.SalesStatus;
import com.mini.joymall.sale.domain.repository.SalesGroupRepository;
import com.mini.joymall.sale.domain.repository.SalesProductRepository;
import com.mini.joymall.seller.domain.entity.Seller;
import com.mini.joymall.seller.domain.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductOptionRepository productOptionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SalesGroupRepository salesGroupRepository;

    @Test
    void 주문_테스트() {
        // given
        int selectedStockA1 = 3;
        int selectedStockA2 = 5;
        int selectedStockB1 = 8;
        Seller sellerA = new Seller("abc@a.com", "1234", "ASeller", "AStore", "010");
        Seller savedSellerA = sellerRepository.save(sellerA);

        Product productA = new Product(savedSellerA.getId(), "아이폰 실리콘 케이스", "아이폰 실리콘 케이스 설명", "아이폰 실리콘 케이스 경로");
        Product productB = new Product(savedSellerA.getId(), "아이폰 가죽 케이스", "아이폰 가죽 케이스 설명", "아이폰 가죽 케이스 경로");
        Product savedProductA = productRepository.save(productA);
        Product savedProductB = productRepository.save(productB);

        ProductOption productOptionA1 = new ProductOption(savedProductA.getId(), "아이폰 15 Pro Max");
        ProductOption productOptionA2 = new ProductOption(savedProductA.getId(), "아이폰 15 Pro");
        ProductOption productOptionB1 = new ProductOption(savedProductB.getId(), "아이폰 13");
        ProductOption savedProductOptionA1 = productOptionRepository.save(productOptionA1);
        ProductOption savedProductOptionA2 = productOptionRepository.save(productOptionA2);
        ProductOption savedProductOptionB1 = productOptionRepository.save(productOptionB1);

        SalesProduct salesProductA1 = new SalesProduct(savedProductOptionA1.getId(), 1000, 50, SalesStatus.ON_SALES);
        SalesProduct salesProductA2 = new SalesProduct(savedProductOptionA2.getId(), 1000, 50, SalesStatus.ON_SALES);
        SalesProduct salesProductB1 = new SalesProduct(savedProductOptionB1.getId(), 1000, 50, SalesStatus.ON_SALES);

        Set<SalesProduct> salesProducts = new HashSet<>();
        salesProducts.add(salesProductA1);
        salesProducts.add(salesProductA2);
        salesProducts.add(salesProductB1);
        SalesGroup salesGroup = new SalesGroup(salesProducts);
        SalesGroup savedSalesGroup = salesGroupRepository.save(salesGroup);
        List<SalesProduct> savedSalesProducts = savedSalesGroup.getSalesProducts().stream().toList();

        Customer customer = new Customer("customer@a.com", "1234", "customerA", "010");
        Customer savedCustomerA = customerRepository.save(customer);
        Location location = new Location("대한민국", "서울시", "강남구", "도산대로", "001-001", "힐스테이트");
        CustomerAddress addressA = new CustomerAddress(savedCustomerA.getId(), "나의 집", "010-1234-1234", location);
        CustomerAddress savedAddressA = addressRepository.save(addressA);

        OrderItem orderItemA1 = new OrderItem(savedSalesProducts.get(0).getId(), selectedStockA1, 1000);
        OrderItem orderItemA2 = new OrderItem(savedSalesProducts.get(1).getId(), selectedStockA2, 1000);
        OrderItem orderItemB1 = new OrderItem(savedSalesProducts.get(2).getId(), selectedStockB1, 1000);

        Set<OrderItem> orderItems = Set.of(orderItemA1, orderItemA2, orderItemB1);
        Order order = new Order(savedAddressA.getId(), orderItems);

        // when
        Order savedOrder = orderRepository.save(order);

        // then
        assertThat(savedOrder.getOrderItems()).contains(orderItemA1, orderItemA2, orderItemB1);
        assertThat(savedOrder.getOrderItems()).hasSize(3);
    }
}