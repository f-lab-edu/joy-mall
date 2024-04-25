package com.mini.joymall.customer.service;

import com.mini.joymall.customer.domain.entity.Address;
import com.mini.joymall.customer.domain.repository.AddressRepository;
import com.mini.joymall.customer.dto.request.CreateAddressRequest;
import com.mini.joymall.customer.dto.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public AddressDTO save(CreateAddressRequest addressDTO) {
        return AddressDTO.from(addressRepository.save(addressDTO.toEntity()));
    }
}
