package com.mini.joymall.customer.controller;

import com.mini.joymall.customer.dto.CustomerDTO;
import com.mini.joymall.customer.dto.CustomerLoginRequest;
import com.mini.joymall.customer.dto.CustomerResponse;
import com.mini.joymall.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> signup(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerResponse customerResponse = customerService.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @PostMapping("/customers/login")
    public ResponseEntity<CustomerResponse> login(@RequestBody @Valid CustomerLoginRequest customerLoginRequest) {
        CustomerResponse customerLoginResponse = customerService.findByEmailAndPassword(customerLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(customerLoginResponse);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> findCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }
}