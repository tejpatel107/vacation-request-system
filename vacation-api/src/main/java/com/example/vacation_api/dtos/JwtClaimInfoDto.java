package com.example.vacation_api.dtos;
import java.util.List;
import java.util.UUID;

import com.example.vacation_api.entities.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtClaimInfoDto {

    private UUID userId;
    private List<RoleType> roles;
}