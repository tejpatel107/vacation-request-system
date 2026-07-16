package com.example.vacation_api.dtos.responseDtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.vacation_api.entities.enums.Status;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class VacationRequestResponseDto {

    private Status status;
    private LocalDateTime requestCreatedAt;
    private LocalDate vacationStartDate;
    private LocalDate vacationEndDate;
    private String comment;
}
