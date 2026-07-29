package com.example.vacation_api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import com.example.vacation_api.entities.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VacationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee author;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "resolved_by", nullable = true)
    private UUID resolvedBy;

    @Column(name = "request_created_at", nullable = false)
    private LocalDateTime requestCreatedAt;
    @Column(name = "vacation_start_date", nullable = false)
    private LocalDate vacationStartDate;
    @Column(name = "vacation_end_date", nullable = false)
    private LocalDate vacationEndDate;
    @Column(name = "comment", nullable = true)
    private String comment;
}
