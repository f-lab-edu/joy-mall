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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void 저장_테스트() {
        // given
        Customer saveCustomer = customerRepository.save(new Customer("abc@abc.com", "1234", "A", "010-1234-5678"));

        // when
        Customer findCustomer = customerRepository.findById(saveCustomer.getId()).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(saveCustomer.getId()).isEqualTo(findCustomer.getId());
    }

    @Test
    void 조회_테스트() {
        // given
        Customer customerA = customerRepository.save(new Customer("a1@a.com", "1234", "A1", "010-1234-5678"));
        Customer customerB = customerRepository.save(new Customer("b1@b.com", "12345", "B1", "010-2345-5678"));

        // when
        Customer findA = customerRepository.findById(customerA.getId()).orElseThrow(NoSuchElementException::new);
        Customer findB = customerRepository.findById(customerB.getId()).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(findA.getId()).isEqualTo(customerA.getId());
        assertThat(findA.getEmail()).isEqualTo(customerA.getEmail());

        assertThat(findB.getId()).isEqualTo(customerB.getId());
        assertThat(findB.getEmail()).isEqualTo(customerB.getEmail());
    }
}