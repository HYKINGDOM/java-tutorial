package com.java.tutorial.project.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimetableSolverException extends RuntimeException {

    private final String jobId;

    private final HttpStatus status;

    public TimetableSolverException(String jobId, HttpStatus status, String message) {
        super(message);
        this.jobId = jobId;
        this.status = status;
    }

    public TimetableSolverException(String jobId, Throwable cause) {
        super(cause.getMessage(), cause);
        this.jobId = jobId;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
