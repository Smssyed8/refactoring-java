/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.exception.customexceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}