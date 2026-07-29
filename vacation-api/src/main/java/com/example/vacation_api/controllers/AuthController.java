package com.example.vacation_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.vacation_api.dtos.requestDtos.LoginRequestDto;
import com.example.vacation_api.dtos.requestDtos.SignupRequestDto;
import com.example.vacation_api.dtos.responseDtos.LoginResponseDto;
import com.example.vacation_api.dtos.responseDtos.SignupResponseDto;
import com.example.vacation_api.security.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        //TODO: process POST request
        
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
    
    
}
