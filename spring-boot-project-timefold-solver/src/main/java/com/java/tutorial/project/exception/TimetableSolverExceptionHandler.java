package com.java.tutorial.project.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author meta
 */
@ControllerAdvice
public class TimetableSolverExceptionHandler {

    @ExceptionHandler({TimetableSolverException.class})
    public ResponseEntity<ErrorInfo> handleTimetableSolverException(TimetableSolverException exception) {
        return new ResponseEntity<>(new ErrorInfo(exception.getJobId(), exception.getMessage()), exception.getStatus());
    }
}
