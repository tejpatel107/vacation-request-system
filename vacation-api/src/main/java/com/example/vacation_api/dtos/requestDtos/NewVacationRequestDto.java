package com.example.vacation_api.dtos.requestDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewVacationRequestDto {
    
    private LocalDate startDate;
    private LocalDate endDate;
    private String comments;
}
