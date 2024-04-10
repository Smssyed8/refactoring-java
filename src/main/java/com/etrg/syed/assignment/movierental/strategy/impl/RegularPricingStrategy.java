/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.strategy.impl;

import com.etrg.syed.assignment.movierental.model.MovieRental;
import com.etrg.syed.assignment.movierental.strategy.PricingStrategy;

import static com.etrg.syed.assignment.movierental.utilities.Constants.*;

public class RegularPricingStrategy implements PricingStrategy {

    @Override
    public double calculateAmount(MovieRental rental) {
        if (rental.getDaysRented() <= REGULAR_BASE_DAYS) {
            return REGULAR_BASE_PRICE;
        } else {
            return REGULAR_BASE_PRICE + (rental.getDaysRented() - REGULAR_BASE_DAYS) * REGULAR_ADDITIONAL_PRICE;
        }
    }

    @Override
    public int calculateFrequentRenterPoints(MovieRental rental) {
        return FREQUENT_RENTER_POINTS_BASE; // Regular movies always earn 1 point per rental
    }
}
