package com.example.vacation_api.controllers;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vacation_api.dtos.requestDtos.NewVacationRequestDto;
import com.example.vacation_api.dtos.responseDtos.VacationRequestResponseDto;
import com.example.vacation_api.entities.User;
import com.example.vacation_api.entities.enums.Status;
import com.example.vacation_api.services.VacationRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("employee/requests")
@RequiredArgsConstructor
public class EmployeeController {

    private final VacationRequestService vacationRequestService;

    @GetMapping
    public ResponseEntity<List<VacationRequestResponseDto>> getVacationRequestsByWorker(@RequestParam Status status,
            Authentication authentication) {
        // Logic to retrieve vacation requests for the given worker ID
        return new ResponseEntity<>(vacationRequestService
                .getAllVacationRequestsForAnEmployee((User) authentication.getPrincipal(), status), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVacationRequestById(@PathVariable UUID id) {
        // Logic to retrieve a specific vacation request by ID
        try {
            return new ResponseEntity<>(vacationRequestService.getVacationRequest(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        
    }

    @GetMapping("/remaining-days")
    public ResponseEntity<?> getRemainingVacationDays(Authentication authentication) {
        // Logic to calculate remaining vacation days
        try {
            return new ResponseEntity<>(vacationRequestService.getRemainingVacationDays((User) authentication.getPrincipal()), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
             return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createNewVacationRequest(
            @RequestBody NewVacationRequestDto requestDto, Authentication authentication) throws BadRequestException {

        try {
            return new ResponseEntity<>(
                    vacationRequestService.createNewVacationRequest(requestDto, (User)authentication.getPrincipal()),
                    HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }
}
