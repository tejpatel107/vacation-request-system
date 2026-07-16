package com.example.vacation_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vacation_api.entities.VacationRequest;

public interface VacationRepository extends JpaRepository<VacationRequest, UUID> {
    
    public VacationRequest findByAuthorId(UUID authorId);
}
