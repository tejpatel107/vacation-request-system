package com.example.vacation_api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import com.example.vacation_api.entities.enums.Status;

public class VacationRequest {
    
    private UUID id;
    private UUID authorId;
    private Status status;
    private UUID resolvedBy;
    private LocalDateTime requestCreatedAt;
    private LocalDate vacationStartDate;
    private LocalDate vacationEndDate;
    private String comment;
}
