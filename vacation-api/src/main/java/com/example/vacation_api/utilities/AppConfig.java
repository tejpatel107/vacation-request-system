package com.example.vacation_api.utilities;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.vacation_api.entities.User;
import com.example.vacation_api.entities.enums.RoleType;

@Configuration
public class AppConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(
        UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder
    )
    {
        
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    // @Bean
    public UserDetailsService inMemoryUserDetailsService() {

        User manager = new User();
        manager.setUsername("manager");
        manager.setPassword(passwordEncoder().encode("pass"));
        manager.setRoles(Set.of(RoleType.MANAGER));

        User employee = new User();
        employee.setUsername("employee");
        employee.setPassword(passwordEncoder().encode("pass"));
        employee.setRoles(Set.of(RoleType.EMPLOYEE));
        
        return new InMemoryUserDetailsManager((UserDetails) manager, (UserDetails) employee);
    }

}
