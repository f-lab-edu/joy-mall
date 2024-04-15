package com.mini.joymall.customer.service;

import com.mini.joymall.customer.domain.entity.Customer;
import com.mini.joymall.customer.domain.repository.CustomerRepository;
import com.mini.joymall.customer.dto.CustomerDTO;
import com.mini.joymall.customer.dto.CustomerLoginRequest;
import com.mini.joymall.customer.dto.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse save(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(customerDTO.toEntity());
        return CustomerResponse.from(savedCustomer);
    }

    public CustomerResponse findByEmailAndPassword(CustomerLoginRequest customerLoginDTO) {
        Customer savedCustomer = customerRepository.findByEmailAndPassword(customerLoginDTO.getEmail(), customerLoginDTO.getPassword());
        return CustomerResponse.from(savedCustomer);
    }

    public List<CustomerResponse> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerResponse::from)
                .toList();
    }
}
