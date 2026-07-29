package com.example.vacation_api.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.vacation_api.dtos.JwtClaimInfoDto;
import com.example.vacation_api.entities.User;
import com.example.vacation_api.entities.enums.RoleType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthUtil {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    private SecretKey getJwtSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(User user) {

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("roles", List.of(user.getEmployee().getRole().name()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(getJwtSecretKey())
                .compact();
    }

    public JwtClaimInfoDto getEmployeeIdAndRolesFromToken(String token) {
        Claims claim = Jwts.parser()
                .verifyWith(getJwtSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        List<?> roleClaims = claim.get("roles", List.class);

        List<RoleType> roles = roleClaims.stream()
                .map(role -> RoleType.valueOf(role.toString()))
                .toList();

        return new JwtClaimInfoDto(
                UUID.fromString(claim.getSubject()),
                roles);

    }

}
