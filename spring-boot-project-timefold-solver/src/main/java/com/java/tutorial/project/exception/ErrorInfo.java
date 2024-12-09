package com.java.tutorial.project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author meta
 */
@AllArgsConstructor
@Data
public class ErrorInfo {

    private String jobId;

    private String message;


}
