package com.example.vacation_api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.vacation_api.entities.VacationRequest;
import com.example.vacation_api.entities.enums.Status;

public interface VacationRepository extends JpaRepository<VacationRequest, UUID> {

    public VacationRequest findByAuthorId(UUID authorId);

    public List<VacationRequest> findByStatus(Status status);

    @Query("""
            SELECT vr
            FROM VacationRequest vr
            WHERE vr.author.id = :employeeId
              AND vr.status = :status
            """)
    public List<VacationRequest> findByEmployeeAndStatus(UUID employeeId, Status status);
}
