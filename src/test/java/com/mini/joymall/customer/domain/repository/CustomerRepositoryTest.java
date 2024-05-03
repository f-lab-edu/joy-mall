package com.mini.joymall.customer.domain.repository;

import com.mini.joymall.customer.domain.entity.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void beforeEach() {
        customerRepository.deleteAll();
    }
    
    @Test
    void 저장_테스트() {
        // given
        Customer saveCustomer = customerRepository.save(new Customer("a@a.com", "1234", "A", "010-1234-5678"));

        // when
        Customer findCustomer = customerRepository.findById(saveCustomer.getId()).get();

        // then
        assertThat(saveCustomer).isEqualTo(findCustomer);
    }

    @Test
    void 조회_테스트() {
        // given
        Customer customerA = customerRepository.save(new Customer("a@a.com", "1234", "A", "010-1234-5678"));
        Customer customerB = customerRepository.save(new Customer("b@b.com", "12345", "B", "010-2345-5678"));

        // when
        List<Customer> customers = (List<Customer>) customerRepository.findAllById(Arrays.asList(customerA.getId(), customerB.getId()));

        // then
        assertThat(customers).contains(customerA, customerB);
    }
}