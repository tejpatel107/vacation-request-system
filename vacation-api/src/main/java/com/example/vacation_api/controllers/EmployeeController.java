package com.example.vacation_api.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vacation_api.entities.VacationRequest;
import com.example.vacation_api.entities.enums.Status;

@RestController
@RequestMapping("employee/requests")
public class EmployeeController {
    
    @GetMapping
    public List<VacationRequest> getVacationRequestsByWorker(@RequestParam Status status) {
        // Logic to retrieve vacation requests for the given worker ID
        return new ArrayList<VacationRequest>();

    }   

    @GetMapping("/{id}")
    public VacationRequest getVacationRequestById(@PathVariable UUID requestId) {
        // Logic to retrieve a specific vacation request by ID
        return new VacationRequest();
    }

    @GetMapping("/remaining-days")
    public int getRemainingVacationDays() {
        // Logic to calculate remaining vacation days
        return 0;
    }
}
