package com.example.vacation_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.vacation_api.entities.VacationRequest;
import com.example.vacation_api.repositories.VacationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VacationRequestService {

    public final VacationRepository vacationRepository;
    
    public List<VacationRequest> getAllVacationRequests(String status) {
        // Logic to retrieve all vacation requests based on the status
        return vacationRepository.findAll();
    }

}
