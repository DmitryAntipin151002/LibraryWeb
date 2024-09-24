package com.example.AuthService.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import lombok.Data;


@Data
@AllArgsConstructor
public class CustomErrorResponse {
    private HttpStatus status;
    private String message;


}
