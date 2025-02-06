package com.example.interntask.repo;

import com.example.interntask.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token,Long> {
    void deleteTokenByAccessToken(String token);
}
