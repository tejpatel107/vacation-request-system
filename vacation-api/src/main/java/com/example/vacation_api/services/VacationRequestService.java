package com.example.vacation_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.vacation_api.dtos.responseDtos.VacationRequestResponseDto;
import com.example.vacation_api.entities.VacationRequest;
import com.example.vacation_api.repositories.VacationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VacationRequestService {

    public final VacationRepository vacationRepository;

    public List<VacationRequestResponseDto> getAllVacationRequests(String status) {
        // Logic to retrieve all vacation requests based on the status
        List<VacationRequest> requests = vacationRepository.findAll();

        System.out.println(requests.get(0).getComment());

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

        System.out.println(res.get(0).getComment());

        return res;
    }

}
