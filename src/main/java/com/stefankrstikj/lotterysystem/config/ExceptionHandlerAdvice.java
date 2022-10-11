package com.stefankrstikj.lotterysystem.config;

import com.stefankrstikj.lotterysystem.exception.NotFoundException;
import com.stefankrstikj.lotterysystem.exception.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception ex,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) {
        String body = ex.getMessage();
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> ongoingLotteryNotFoundExceptionHandler(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
