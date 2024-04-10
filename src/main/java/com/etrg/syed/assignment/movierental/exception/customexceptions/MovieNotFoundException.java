/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.exception.customexceptions;

/**
 * Custom Exception for MovieNotFoundException
 */
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}