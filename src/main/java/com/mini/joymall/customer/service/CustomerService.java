package com.mini.joymall.customer.service;

import com.mini.joymall.customer.domain.entity.Customer;
import com.mini.joymall.customer.domain.repository.CustomerAddressRepository;
import com.mini.joymall.customer.domain.repository.CustomerRepository;
import com.mini.joymall.customer.dto.CustomerAddressDTO;
import com.mini.joymall.customer.dto.CustomerDTO;
import com.mini.joymall.customer.dto.request.CustomerLoginRequest;
import com.mini.joymall.customer.dto.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository addressRepository;

    public CustomerResponse save(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(customerDTO.toEntity());
        return CustomerResponse.from(savedCustomer);
    }

    public CustomerResponse findByEmailAndPassword(CustomerLoginRequest customerLoginRequest) {
        Customer savedCustomer = customerRepository.findByEmailAndPassword(customerLoginRequest.getEmail(), customerLoginRequest.getPassword());
        return CustomerResponse.from(savedCustomer);
    }

    public CustomerAddressDTO findAddressByCustomerId(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        return CustomerAddressDTO.from(addressRepository.findByCustomerId(customer.getId()).orElseThrow(NoSuchElementException::new));
    }
}
