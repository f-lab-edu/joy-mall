package com.mini.joymall.customer.domain.repository;

import com.mini.joymall.customer.domain.entity.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;`

    @AfterEach
    void afterEach() {
        customerRepository.deleteAll();
    }
    
    @Test
    void 저장_테스트() {
        // given
        Customer saveCustomer = customerRepository.save(new Customer("a@a.com", "1234", "A", "010-1234-5678"));

        // when
        Customer findCustomer = customerRepository.findAll().get(0);

        // then
        assertThat(saveCustomer).isEqualTo(findCustomer);
    }

    @Test
    void 조회_테스트() {
        // given
        Customer customerA = customerRepository.save(new Customer("a@a.com", "1234", "A", "010-1234-5678"));
        Customer customerB = customerRepository.save(new Customer("b@b.com", "12345", "B", "010-2345-5678"));

        // when
        List<Customer> customers = customerRepository.findAll();

        // then
        assertThat(customers).contains(customerA, customerB);
    }
}