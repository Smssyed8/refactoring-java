/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.service;

import com.etrg.syed.assignment.movierental.model.Customer;
import com.etrg.syed.assignment.movierental.model.Movie;

public interface RentalService {
    String statement(Customer customer);
    Movie findMovieById(String movieId);
}
