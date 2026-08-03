package com.example.vacation_api.security;

import java.time.Year;
import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vacation_api.dtos.requestDtos.LoginRequestDto;
import com.example.vacation_api.dtos.requestDtos.SignupRequestDto;
import com.example.vacation_api.dtos.responseDtos.LoginResponseDto;
import com.example.vacation_api.dtos.responseDtos.SignupResponseDto;
import com.example.vacation_api.entities.Employee;
import com.example.vacation_api.entities.User;
import com.example.vacation_api.entities.VacationDaysBalance;
import com.example.vacation_api.entities.enums.RoleType;
import com.example.vacation_api.repositories.EmployeeRepository;
import com.example.vacation_api.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUtil authUtil;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        // TODO Auto-generated method stub

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateJwtToken(user);

        return new LoginResponseDto(token, user.getId());
    }

    @Transactional
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        // TODO Auto-generated method stub

        log.info("Creating a user");

        User user = userRepository.findByUsername(signupRequestDto.getUsername())
                .orElse(null);

        if (user != null)
            throw new IllegalArgumentException("User already exists");

        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        RoleType role = signupRequestDto.getRole();

        Employee emp = Employee.builder()
                .name(signupRequestDto.getName())
                .role(signupRequestDto.getRole())
                .hiredDate(signupRequestDto.getHiredDate())
                .build();

        VacationDaysBalance balance = VacationDaysBalance
                                            .builder()
                                            .employee(emp)
                                            .year(Year.of(signupRequestDto.getHiredDate().getYear()))
                                            .build();
        
        emp.getVacationDaysBalance().add(balance);

        user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(Set.of(role))
                .employee(emp)
                .build();

        emp.setUser(user);
        userRepository.save(user);

        return new SignupResponseDto(user.getId(), username);
    }

}
