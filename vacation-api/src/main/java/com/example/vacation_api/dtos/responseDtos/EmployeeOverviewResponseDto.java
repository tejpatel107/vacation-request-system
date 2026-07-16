package com.example.vacation_api.dtos.responseDtos;

import java.util.List;
import java.util.UUID;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class EmployeeOverviewResponseDto {
    
    private UUID id;
    private String name;
    private int totalVacationDaysPerYear;
    private int remainingVacationDays;
    private List<VacationRequestResponseDto> pendingVacationRequest;
    private List<VacationRequestResponseDto> approvedVacationRequest;
}
