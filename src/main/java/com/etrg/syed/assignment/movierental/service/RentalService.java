package com.etrg.syed.assignment.movierental.service;

import com.etrg.syed.assignment.movierental.model.Customer;
import com.etrg.syed.assignment.movierental.model.Movie;

/**
 * Service Interface for Calculating total amount statement and find Movie By Id
 */
public interface RentalService {
    String statement(Customer customer);
    Movie findMovieById(String movieId);
}
