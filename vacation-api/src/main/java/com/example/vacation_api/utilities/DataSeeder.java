package com.example.vacation_api.utilities;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.vacation_api.entities.Employee;
import com.example.vacation_api.entities.VacationRequest;
import com.example.vacation_api.entities.enums.RoleType;
import com.example.vacation_api.repositories.EmployeeRepository;
import com.example.vacation_api.repositories.VacationRepository;
import com.example.vacation_api.entities.enums.Status;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    
    private final EmployeeRepository employeeRepository;
    private final VacationRepository vacationRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Seed data logic here

        Employee employee1 = new Employee();
        employee1.setName("John Doe");
        employee1.setRole(RoleType.EMPLOYEE);
        employee1.setHiredDate(LocalDate.of(2020, 1, 15));

        Employee employee2 = new Employee();
        employee2.setName("Jane Smith");
        employee2.setRole(RoleType.MANAGER);
        employee2.setHiredDate(LocalDate.of(2019, 6, 1));

        Employee employee3 = new Employee();
        employee3.setName("Alice Johnson");
        employee3.setRole(RoleType.EMPLOYEE);
        employee3.setHiredDate(LocalDate.of(2021, 3, 10));

        Employee employee4 = new Employee();
        employee4.setName("Bob Brown");
        employee4.setRole(RoleType.MANAGER);
        employee4.setHiredDate(LocalDate.of(2020, 9, 20));

        employeeRepository.save(employee1); // Clear existing data
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);

        VacationRequest vacationRequest1 = new VacationRequest();
        vacationRequest1.setAuthor(employee1);
        vacationRequest1.setStatus(Status.PENDING);
        vacationRequest1.setRequestCreatedAt(java.time.LocalDateTime.now());
        vacationRequest1.setVacationStartDate(LocalDate.now());
        vacationRequest1.setVacationEndDate(LocalDate.now().plusDays(7));
        vacationRequest1.setComment("Family vacation");

        VacationRequest vacationRequest2 = new VacationRequest();
        vacationRequest2.setAuthor(employee2);
        vacationRequest2.setStatus(Status.PENDING);
        vacationRequest2.setRequestCreatedAt(java.time.LocalDateTime.now());
        vacationRequest2.setVacationStartDate(LocalDate.now());
        vacationRequest2.setVacationEndDate(LocalDate.now().plusDays(3));
        vacationRequest2.setComment("Personal time off");

        VacationRequest vacationRequest3 = new VacationRequest();
        vacationRequest3.setAuthor(employee3);
        vacationRequest3.setStatus(Status.PENDING);
        vacationRequest3.setRequestCreatedAt(java.time.LocalDateTime.now());
        vacationRequest3.setVacationStartDate(LocalDate.now());
        vacationRequest3.setVacationEndDate(LocalDate.now().plusDays(5));
        vacationRequest3.setComment("Vacation for a wedding");

        VacationRequest vacationRequest4 = new VacationRequest();
        vacationRequest4.setAuthor(employee1);
        vacationRequest4.setStatus(Status.PENDING);
        vacationRequest4.setRequestCreatedAt(java.time.LocalDateTime.now());
        vacationRequest4.setVacationStartDate(LocalDate.of(2026, 11, 1));
        vacationRequest4.setVacationEndDate(LocalDate.now().plusWeeks(2));
        vacationRequest4.setComment("Extended vacation for personal reasons");

        vacationRepository.save(vacationRequest1);
        vacationRepository.save(vacationRequest2);
        vacationRepository.save(vacationRequest3);
        vacationRepository.save(vacationRequest4);
    }

}
