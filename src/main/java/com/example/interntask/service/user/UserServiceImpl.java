package com.example.interntask.service.user;

import com.example.interntask.model.entity.User;
import com.example.interntask.repo.UsersRepo;
import com.example.interntask.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UsersRepo userRepo;
    private final JwtTokenUtil jwtUtil;


    private void validateUserId(Long id, String token) {
        String jwtToken = jwtUtil.extractTokenFromHeader(token);
        User user = jwtUtil.getUserById(jwtToken);
        if (user.getId() == null) {
            throw new RuntimeException("Token is invalid");
        }
        if (!user.getId().equals(id)) {
            throw new RuntimeException("Access denied: You can only access your own data");
        }
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepo.findUserByUserName(username).orElseThrow();
    }

}