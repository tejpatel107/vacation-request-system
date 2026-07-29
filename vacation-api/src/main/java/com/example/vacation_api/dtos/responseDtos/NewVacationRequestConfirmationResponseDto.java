package com.example.vacation_api.dtos.responseDtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.vacation_api.entities.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewVacationRequestConfirmationResponseDto {
    
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private Status status;
    private String comment;
}
