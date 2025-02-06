package com.example.interntask.util;

import com.example.interntask.globalException.exceptions.AlreadyExistsException;
import com.example.interntask.model.dto.UsersDto;
import com.example.interntask.repo.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidationUtil {
    private final UsersRepository usersRepository;

    public void checkUserEmail(UsersDto usersDto) {
        var user = usersRepository.findByEmailOrUsername(usersDto.getEmail(), usersDto.getUsername());
        if (user.isPresent()) {
            throw new AlreadyExistsException("User already exists with " + usersDto.getEmail());
        }
    }

}
