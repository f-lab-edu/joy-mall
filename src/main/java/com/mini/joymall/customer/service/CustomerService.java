package com.mini.joymall.customer.service;

import com.mini.joymall.customer.domain.entity.Address;
import com.mini.joymall.customer.domain.entity.Customer;
import com.mini.joymall.customer.domain.repository.AddressRepository;
import com.mini.joymall.customer.domain.repository.CustomerRepository;
import com.mini.joymall.customer.dto.AddressDTO;
import com.mini.joymall.customer.dto.CustomerDTO;
import com.mini.joymall.customer.dto.CustomerLoginRequest;
import com.mini.joymall.customer.dto.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerResponse save(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(customerDTO.toEntity());
        return CustomerResponse.from(savedCustomer);
    }

    public CustomerResponse findByEmailAndPassword(CustomerLoginRequest customerLoginRequest) {
        Customer savedCustomer = customerRepository.findByEmailAndPassword(customerLoginRequest.getEmail(), customerLoginRequest.getPassword());
        return CustomerResponse.from(savedCustomer);
    }

    public AddressDTO findAddressByCustomerId(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        return AddressDTO.from(addressRepository.findByCustomerId(customer.getId()).orElseThrow(NoSuchElementException::new));
    }
}
