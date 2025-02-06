package com.example.interntask.service.token;

import com.example.interntask.repo.TokenRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {
    private final TokenRepo tokenRepo;

    @Override
    public void deleteTokenByAccessToken(String token) {
        tokenRepo.deleteTokenByAccessToken(token);
    }
}