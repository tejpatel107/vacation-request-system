package com.example.vacation_api.dtos.responseDtos;

import java.time.Year;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemainingDaysResponseDto {
    
    private UUID employerId;
    private Year year;
    private int totalVacationDays;
    private int totalApprovedDays;
    private int remainingDays;

}
