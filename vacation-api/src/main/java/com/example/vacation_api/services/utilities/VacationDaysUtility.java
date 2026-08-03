package com.example.vacation_api.services.utilities;

import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.example.vacation_api.dtos.requestDtos.NewVacationRequestDto;
import com.example.vacation_api.entities.Employee;
import com.example.vacation_api.entities.VacationDaysBalance;
import com.example.vacation_api.entities.VacationRequest;

public class VacationDaysUtility {

    public static int getRemainingVacationDaysBalance(Employee employee) {

        List<VacationDaysBalance> vacationDaysBalance = employee.getVacationDaysBalance();

        int remainingDays = 0;

        for (VacationDaysBalance balance : vacationDaysBalance) {
            if (balance.getYear().equals(Year.now())) {
                System.out.println(balance.getRemainingVacationDays());
                remainingDays += balance.getRemainingVacationDays();
            }
        }

        return remainingDays;
    }

    private static boolean doesVacationRequestOfSamePeriodExists(Employee employee, NewVacationRequestDto requestDto) {

        List<VacationRequest> vacationRequests = employee.getVacationRequests();

        for (VacationRequest request : vacationRequests) {

            if (request.getVacationStartDate().equals(requestDto.getStartDate())
                    ||
                    request.getVacationEndDate().equals(requestDto.getEndDate())) {
                return true;
            }

        }

        return false;
    }

    public static void validate(Employee employee, NewVacationRequestDto requestDto)
            throws Exception {

        int requestedDays = Math.abs((int) ChronoUnit.DAYS.between(
                requestDto.getStartDate(),
                requestDto.getEndDate())) + 1;

        if (requestedDays > VacationDaysUtility.getRemainingVacationDaysBalance(employee)) {

            throw new Exception(
                    "Total approved days including current requested days has exceeded your total vacation days for the year. You only have remaining balance of "
                            + VacationDaysUtility.getRemainingVacationDaysBalance(employee));

        } else if (doesVacationRequestOfSamePeriodExists(employee, requestDto)) {

            throw new Exception(
                    "A request has already been made for similar dates. Please check your dates, and make request again. Thank you.");
        }
    }
}
