package com.mini.joymall.customer.service;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.repository.CustomerAddressRepository;
import com.mini.joymall.customer.dto.request.CreateCustomerAddressRequest;
import com.mini.joymall.customer.dto.CustomerAddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerAddressService {
    private final CustomerAddressRepository addressRepository;
    public CustomerAddressDTO save(CreateCustomerAddressRequest createCustomerAddressRequest) {
        return CustomerAddressDTO.from(addressRepository.save(createCustomerAddressRequest.toEntity()));
    }
}
