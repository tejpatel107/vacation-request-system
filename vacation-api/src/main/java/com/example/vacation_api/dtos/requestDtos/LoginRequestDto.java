package com.example.vacation_api.dtos.requestDtos;

import com.example.vacation_api.entities.enums.RoleType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * LoginRequestDto
 */
@Data
@Getter
@Setter
public class LoginRequestDto {

    String username;
    String password;
    RoleType role;
}
