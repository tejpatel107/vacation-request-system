package com.example.vacation_api.services;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vacation_api.dtos.requestDtos.NewVacationRequestDto;
import com.example.vacation_api.dtos.responseDtos.NewVacationRequestConfirmationResponseDto;
import com.example.vacation_api.dtos.responseDtos.RemainingDaysResponseDto;
import com.example.vacation_api.dtos.responseDtos.VacationRequestResponseDto;
import com.example.vacation_api.entities.Employee;
import com.example.vacation_api.entities.User;
import com.example.vacation_api.entities.VacationRequest;
import com.example.vacation_api.entities.enums.Status;
import com.example.vacation_api.repositories.EmployeeRepository;
import com.example.vacation_api.repositories.VacationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VacationRequestService {

    public final VacationRepository vacationRepository;
    public final EmployeeRepository employeeRepository;

    // service consumed by manager api
    public List<VacationRequestResponseDto> getAllVacationRequests(Status status) {
        // Logic to retrieve all vacation requests based on the status

        List<VacationRequest> requests = new ArrayList<>();

        if (status == null) {
            requests = vacationRepository.findAll();
        } else {
            requests = vacationRepository.findByStatus(status);
        }

        List<VacationRequestResponseDto> res = new ArrayList<>();

        for (int i = 0; i < requests.size(); i++) {

            VacationRequestResponseDto dto = new VacationRequestResponseDto(
                    requests.get(i).getStatus(),
                    requests.get(i).getRequestCreatedAt(),
                    requests.get(i).getVacationStartDate(),
                    requests.get(i).getVacationEndDate(),
                    requests.get(i).getComment());

            res.add(dto);
        }
        return res;
    }

    public List<VacationRequestResponseDto> getAllVacationRequestsForAnEmployee(User user, Status status) {
        List<VacationRequest> requests = new ArrayList<>();

        if (status == null) {
            requests = vacationRepository.findAll();
        } else {
            requests = vacationRepository.findByEmployeeAndStatus(user.getEmployee().getId(), status);
        }

        List<VacationRequestResponseDto> res = new ArrayList<>();

        for (int i = 0; i < requests.size(); i++) {

            VacationRequestResponseDto dto = new VacationRequestResponseDto(
                    requests.get(i).getStatus(),
                    requests.get(i).getRequestCreatedAt(),
                    requests.get(i).getVacationStartDate(),
                    requests.get(i).getVacationEndDate(),
                    requests.get(i).getComment());

            res.add(dto);
        }
        return res;
    }

    public VacationRequestResponseDto getVacationRequest(UUID requestId) throws Exception{

        VacationRequest request = vacationRepository.findById(requestId).orElse(null);

        if (request == null) {
            throw new Exception("Request not found for the id provided");
        }

        return VacationRequestResponseDto.builder()
                    .comment(request.getComment())
                    .status(request.getStatus())
                    .vacationStartDate(request.getVacationStartDate())
                    .vacationEndDate(request.getVacationEndDate())
                    .requestCreatedAt(request.getRequestCreatedAt())
                    .build();
    }

    @Transactional
    public NewVacationRequestConfirmationResponseDto createNewVacationRequest(NewVacationRequestDto requestDto,
            User user) throws Exception {

        Employee author = employeeRepository.findById(user.getEmployee().getId()).orElse(null);

        int requestedDays = Math.abs((int) ChronoUnit.DAYS.between(
                requestDto.getStartDate(),
                requestDto.getEndDate())) + 1;

        if (requestedDays > author.getRemainingVacationDays()) {
            throw new Exception(
                    "Total approved days including current requested days has exceeded your total vacation days for the year.");
        }

        VacationRequest vacationRequest = VacationRequest.builder()
                .author(author)
                .status(Status.PENDING)
                .requestCreatedAt(LocalDateTime.now())
                .comment(requestDto.getComments())
                .vacationStartDate(requestDto.getStartDate())
                .vacationEndDate(requestDto.getEndDate())
                .build();

        vacationRepository.save(vacationRequest);

        author.getVacationRequests().add(vacationRequest);
        author.setRemainingVacationDays(author.getRemainingVacationDays() - requestedDays);
        employeeRepository.save(author);

        return NewVacationRequestConfirmationResponseDto.builder()
                .comment(vacationRequest.getComment())
                .createdAt(vacationRequest.getRequestCreatedAt())
                .startDate(vacationRequest.getVacationStartDate())
                .endDate(vacationRequest.getVacationEndDate())
                .status(vacationRequest.getStatus())
                .build();
    }

    public RemainingDaysResponseDto getRemainingVacationDays(User user) {
        // TODO Auto-generated method stub
        
        Employee emp = employeeRepository.findById(user.getEmployee().getId()).orElse(null);

        return RemainingDaysResponseDto
                    .builder()
                    .employerId(emp.getId())
                    .remainingDays(emp.getRemainingVacationDays())
                    .totalVacationDays(emp.getTotalVacationDaysPerYear())
                    .totalApprovedDays(emp.getTotalVacationDaysPerYear() - emp.getRemainingVacationDays())
                    .year(Year.now())
                    .build();
    }

}
