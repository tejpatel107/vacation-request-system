package com.example.vacation_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vacation_api.dtos.responseDtos.EmployeeOverviewResponseDto;
import com.example.vacation_api.entities.VacationRequest;
import com.example.vacation_api.services.EmployeeService;
import com.example.vacation_api.services.VacationRequestService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin")
public class ManagerController {
    
    public final EmployeeService employeeService;
    public final VacationRequestService vacationRequestService;

    @GetMapping("/requests")
    public List<VacationRequest> getAllVacationRequests(@RequestParam(required = false) String status) {
        // Constructor logic if needed
        return vacationRequestService.getAllVacationRequests(status);
    }

    @GetMapping("/employees/{employeeId}/overview")
    public EmployeeOverviewResponseDto getEmployeeOverview(@PathVariable UUID employeeId) {

        return employeeService.getEmployeeOverview(employeeId);
    }
    

}
