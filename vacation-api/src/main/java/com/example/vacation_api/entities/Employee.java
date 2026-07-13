package com.example.vacation_api.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.example.vacation_api.entities.enums.RoleType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    
    private UUID id;
    
    private String name;

    private RoleType role;

    private final int totalVacationDaysPerYear = 30;

    private LocalDate hiredDate;
}