package com.example.interntask.util;

import com.example.interntask.globalException.exceptions.AlreadyExistsException;
import com.example.interntask.model.dto.UsersDto;
import com.example.interntask.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidationUtil {
    private final UsersRepo usersRepo;

    public void checkUserEmail(UsersDto usersDto) {
        var user = usersRepo.findByEmailOrUsername(usersDto.getEmail(), usersDto.getUsername());
        if (user.isPresent()) {
            throw new AlreadyExistsException("User already exists with " + usersDto.getEmail());
        }
    }

}
