/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.strategy;

import com.etrg.syed.assignment.movierental.model.MovieRental;

public interface PricingStrategy {
    double calculateAmount(MovieRental rental);
    int calculateFrequentRenterPoints(MovieRental rental);
}
