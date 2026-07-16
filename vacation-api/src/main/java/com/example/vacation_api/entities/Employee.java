package com.example.vacation_api.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.vacation_api.entities.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "employee_name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_role", nullable = false)
    private RoleType role;

    // @Column(name = "total_vacation_days_per_year")
    private final int totalVacationDaysPerYear = 30;

    @Column(name = "remaining_vacation_days")
    private int remainingVacationDays;

    @Column(name = "hired_date")
    private LocalDate hiredDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "author")
    private List<VacationRequest> vacationRequests = new ArrayList<>();


}