package com.example.interntask.service.auth;

import com.example.interntask.model.dto.UsersDto;
import com.example.interntask.model.request.JwtRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> login(JwtRequest authenticationRequest);

    void register(UsersDto usersDto);

    void logout(String token);

}
