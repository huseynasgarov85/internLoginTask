package com.example.interntask.service.auth;

import com.example.interntask.globalException.exceptions.AuthenticationException;
import com.example.interntask.mapper.UsersMapper;
import com.example.interntask.model.dto.UsersDto;
import com.example.interntask.model.request.JwtRequest;
import com.example.interntask.model.response.JwtResponse;
import com.example.interntask.repo.UsersRepository;
import com.example.interntask.service.security.SecurityService;
import com.example.interntask.service.token.TokenService;
import com.example.interntask.util.JwtTokenUtil;
import com.example.interntask.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationImplService implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityService userDetailService;
    private final UsersMapper usersMapper;
    private final UsersRepository usersRepository;
    private final ValidationUtil validationUtil;
    private final TokenService tokenService;


    @Override
    public ResponseEntity<?> login(JwtRequest authenticationRequest) {
        log.info("ActionLog.createAuthenticationToken.started : authenticationRequest {}", authenticationRequest);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("incorrect username or password");
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(jwt);
        log.info("ActionLog.createAuthenticationToken.end : authenticationRequest {}", authenticationRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @Override
    public void register(UsersDto usersDto) {
        validationUtil.checkUserEmail(usersDto);
        log.info("ActionLog.add.started:userDto {}", usersDto);
        usersDto.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        var userEntity = usersMapper.mapToEntity(usersDto);
        userEntity.setDateTimeOfCreation(LocalDate.now().atStartOfDay());
        usersRepository.save(userEntity);
        log.info("ActionLog.add.end:userDto {}", usersDto);
    }

    @Override
    public void logout(String token) {
        String data = token.substring(7);
        tokenService.deleteTokenByAccessToken(data);
    }

}
