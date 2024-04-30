package com.mini.joymall.customer.controller;

import com.mini.joymall.commons.ApiResponse;
import com.mini.joymall.customer.dto.AddressDTO;
import com.mini.joymall.customer.dto.CustomerDTO;
import com.mini.joymall.customer.dto.CustomerLoginRequest;
import com.mini.joymall.customer.dto.CustomerResponse;
import com.mini.joymall.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mini.joymall.commons.ApiResponse.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> signup(@RequestBody @Valid CustomerDTO customerDTO) {
        return OK(customerService.save(customerDTO));
    }

    @PostMapping("/customers/login")
    public ResponseEntity<CustomerResponse> login(@RequestBody @Valid CustomerLoginRequest customerLoginRequest) {
        return OK(customerService.findByEmailAndPassword(customerLoginRequest));
    }

    @GetMapping("/customers/{id}/address")
    public ResponseEntity<AddressDTO> findAddressByCustomerId(@PathVariable Long id) {
        return OK(customerService.findAddressByCustomerId(id));
    }
}