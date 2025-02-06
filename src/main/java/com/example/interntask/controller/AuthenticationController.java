package com.example.interntask.controller;

import com.example.interntask.model.dto.UsersDto;
import com.example.interntask.model.request.JwtRequest;
import com.example.interntask.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid UsersDto usersDto) {
        authenticationService.register(usersDto);
    }
    @PostMapping("/logout")
    @Operation(
            summary = "User Logout",
            description = "Endpoint for user logout. This API invalidates the provided access token, logging out the user."
    )
    public ResponseEntity.BodyBuilder logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        authenticationService.logout(token);
        return ResponseEntity.ok();
    }
}
