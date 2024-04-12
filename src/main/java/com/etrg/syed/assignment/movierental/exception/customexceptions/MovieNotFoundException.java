package com.etrg.syed.assignment.movierental.exception.customexceptions;

/** Custom Exception for MovieNotFoundException */
public final class MovieNotFoundException extends RuntimeException {
  public MovieNotFoundException(String message) {
    super(message);
  }
}
