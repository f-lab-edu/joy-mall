package com.mini.joymall.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {
    public static <T> ResponseEntity<T> OK(T object) {
        return ResponseEntity.ok().body(object);
    }

    public static final ResponseEntity.BodyBuilder OK = ResponseEntity.status(HttpStatus.OK);
}