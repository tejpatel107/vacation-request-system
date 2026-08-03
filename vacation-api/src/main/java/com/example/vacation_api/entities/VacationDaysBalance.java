package com.example.vacation_api.entities;
import java.time.Year;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VacationDaysBalance {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @Column(name = "balance_for_year")
    private Year year = Year.now();

    @Column(name = "total_vacation_days_allowed")
    private final int totalAllowed = 30;

    @Column(name = "approved_vacation_days")
    private int totalApproved = 0;

    @Column(name = "remaining_vacation_days")
    @Builder.Default
    private int remainingVacationDays = 30;

}