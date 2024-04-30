package com.mini.joymall.customer.controller;

import com.mini.joymall.commons.ApiResponse;
import com.mini.joymall.customer.dto.request.CreateCustomerAddressRequest;
import com.mini.joymall.customer.dto.CustomerAddressDTO;
import com.mini.joymall.customer.service.CustomerAddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mini.joymall.commons.ApiResponse.*;

@RestController
@AllArgsConstructor
public class CustomerAddressController {
    private final CustomerAddressService addressService;

    @PostMapping("/addresses")
    public ResponseEntity<CustomerAddressDTO> save(@RequestBody @Valid CreateCustomerAddressRequest addressDTO) {
        return OK(addressService.save(addressDTO));
    }
}
