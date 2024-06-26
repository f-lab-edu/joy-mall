package com.mini.joymall.customer.controller;

import com.mini.joymall.customer.dto.CustomerAddressDTO;
import com.mini.joymall.customer.dto.CustomerDTO;
import com.mini.joymall.customer.dto.request.CustomerLoginRequest;
import com.mini.joymall.customer.dto.response.CustomerResponse;
import com.mini.joymall.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CustomerAddressDTO> findAddressByCustomerId(@PathVariable Long id) {
        return OK(customerService.findAddressByCustomerId(id));
    }
}