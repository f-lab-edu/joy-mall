package com.mini.joymall.customer.domain.repository;

import com.mini.joymall.customer.domain.entity.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    
    @Test
    void Customer_저장_테스트() {
        // given, when
        Customer customerA = customerRepository.save(new Customer("a@a.com", "1234", "A", "010-1234-5678"));

        // then
        Customer findCustomer = customerRepository.findById(1L)
                .orElse(null);
//
//        assertThat(customerA).isEqualTo(findCustomer);
    }

    @Test
    void Customer_조회_테스트() {
        customerRepository.findAll();
    }
}