/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.exception;

import com.etrg.syed.assignment.movierental.exception.customexceptions.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This request is invalid: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        String bodyOfResponse = "An unexpected error occurred: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = { MovieNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Movie not found: " + ex.getMessage();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.NOT_FOUND);
    }
}
