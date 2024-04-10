package com.etrg.syed.assignment.movierental.strategy.impl;

import com.etrg.syed.assignment.movierental.model.MovieRental;

import static com.etrg.syed.assignment.movierental.utilities.Constants.REGULAR_BASE_DAYS;
import static com.etrg.syed.assignment.movierental.utilities.Constants.REGULAR_ADDITIONAL_PRICE;
import static com.etrg.syed.assignment.movierental.utilities.Constants.REGULAR_BASE_PRICE;
import static com.etrg.syed.assignment.movierental.utilities.Constants.FREQUENT_RENTER_POINTS_BASE;

/**
 * Pricing Strategy for Calculating amount and getting FrequentRenterPoints for Regular category
 */
public final class RegularPricingStrategy implements PricingStrategy {

    @Override
    public double calculateAmount(MovieRental rental) {
        if (rental.daysRented() <= REGULAR_BASE_DAYS) {
            return REGULAR_BASE_PRICE;
        } else {
            return REGULAR_BASE_PRICE + (rental.daysRented() - REGULAR_BASE_DAYS) * REGULAR_ADDITIONAL_PRICE;
        }
    }

    @Override
    public int calculateFrequentRenterPoints(MovieRental rental) {
        return FREQUENT_RENTER_POINTS_BASE; // Regular movies earn 1 point per rental
    }
}
