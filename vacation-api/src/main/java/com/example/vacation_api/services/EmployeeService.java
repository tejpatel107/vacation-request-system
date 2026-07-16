package com.example.vacation_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.vacation_api.dtos.responseDtos.EmployeeOverviewResponseDto;
import com.example.vacation_api.dtos.responseDtos.VacationRequestResponseDto;
import com.example.vacation_api.entities.Employee;
import com.example.vacation_api.entities.VacationRequest;
import com.example.vacation_api.entities.enums.Status;
import com.example.vacation_api.repositories.EmployeeRepository;
import com.example.vacation_api.repositories.VacationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final VacationRepository vacationRepository;

    public List<VacationRequest> getVacationRequestsByWorker(UUID employeeId, Status status) {
        // Logic to retrieve vacation requests for the given worker ID and status

        vacationRepository.findByAuthorId(employeeId);

        return new ArrayList<VacationRequest>();
    }

    public EmployeeOverviewResponseDto getEmployeeOverview(UUID employeeId) {
        // Logic to retrieve employee overview for the given employee ID

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RuntimeException("Employee not found with ID: " + employeeId));

        List<VacationRequestResponseDto> pendingVacationRequests = employee.getVacationRequests().stream()
                .filter(request -> request.getStatus() == Status.PENDING)
                .map(request -> {
                    VacationRequestResponseDto dto = new VacationRequestResponseDto();
                    dto.setStatus(request.getStatus());
                    dto.setVacationStartDate(request.getVacationStartDate());
                    dto.setVacationEndDate(request.getVacationEndDate());
                    dto.setComment(request.getComment());
                    dto.setRequestCreatedAt(request.getRequestCreatedAt());
                    return dto;
                })
                .toList();

        for (VacationRequestResponseDto request : pendingVacationRequests) {
            System.out.println("Status: " + request.getStatus());
            System.out.println("Start Date: " + request.getVacationStartDate());
            System.out.println("End Date: " + request.getVacationEndDate());
            System.out.println("Comment: " + request.getComment());
            System.out.println("----------------------------");
        }

        List<VacationRequestResponseDto> approvedVacationRequests = employee.getVacationRequests().stream()
                .filter(request -> request.getStatus() == Status.APPROVED)
                .map(request -> {
                    VacationRequestResponseDto dto = new VacationRequestResponseDto();
                    dto.setStatus(request.getStatus());
                    dto.setVacationStartDate(request.getVacationStartDate());
                    dto.setVacationEndDate(request.getVacationEndDate());
                    dto.setComment(request.getComment());
                    dto.setRequestCreatedAt(request.getRequestCreatedAt());
                    return dto;
                })
                .toList();

        EmployeeOverviewResponseDto overviewDto = new EmployeeOverviewResponseDto();
        overviewDto.setId(employee.getId());
        overviewDto.setName(employee.getName());
        overviewDto.setTotalVacationDaysPerYear(employee.getTotalVacationDaysPerYear());
        overviewDto.setRemainingVacationDays(employee.getRemainingVacationDays());
        overviewDto.setPendingVacationRequest(pendingVacationRequests.isEmpty() ? null : pendingVacationRequests);
        overviewDto.setApprovedVacationRequest(approvedVacationRequests.isEmpty() ? null : approvedVacationRequests);

        System.out.println("Employee Overview: " + overviewDto);

        return overviewDto;
    }
}
