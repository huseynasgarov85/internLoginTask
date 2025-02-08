package com.example.interntask.globalException;

import com.example.interntask.globalException.exceptions.AlreadyExistsException;
import com.example.interntask.globalException.exceptions.AuthenticationException;
import com.example.interntask.globalException.exceptions.Bearer_Token;
import com.example.interntask.globalException.exceptions.NotFoundException;
import com.example.interntask.model.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDto handleAuthenticatedException(AuthenticationException e) {
        log.error("ActionLog error " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundException(NotFoundException e) {
        log.error("ActionLog is failed: {}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handleAlreadyExistsException(AlreadyExistsException e) {
        log.error("ActionLog is failed: {}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(Bearer_Token.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDto handleUnexpectedException(Bearer_Token e) {
        log.error("ActionLog error " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }
}