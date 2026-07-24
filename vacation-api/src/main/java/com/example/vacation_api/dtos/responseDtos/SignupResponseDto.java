package com.example.vacation_api.dtos.responseDtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SignupResponseDto {
    private UUID userId;
    private String username;
}
