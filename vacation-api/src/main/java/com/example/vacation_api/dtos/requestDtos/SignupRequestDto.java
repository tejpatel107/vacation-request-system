package com.example.vacation_api.dtos.requestDtos;

import java.time.LocalDate;

import com.example.vacation_api.entities.enums.RoleType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignupRequestDto {
    
    public String username;
    public String password;
    public String name;
    public RoleType role;
    public LocalDate hiredDate;
}
