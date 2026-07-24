package com.example.vacation_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.vacation_api.entities.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    
}
