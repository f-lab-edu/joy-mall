package com.mini.joymall.customer.controller;

import com.mini.joymall.customer.domain.entity.Address;
import com.mini.joymall.customer.dto.request.CreateAddressRequest;
import com.mini.joymall.customer.dto.AddressDTO;
import com.mini.joymall.customer.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> create(@RequestBody @Valid CreateAddressRequest addressDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.save(addressDTO));
    }
}
