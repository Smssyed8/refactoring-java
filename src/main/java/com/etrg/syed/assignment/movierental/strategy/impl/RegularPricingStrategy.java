/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.strategy.impl;

import com.etrg.syed.assignment.movierental.model.MovieRental;
import com.etrg.syed.assignment.movierental.strategy.PricingStrategy;

public class RegularPricingStrategy implements PricingStrategy {
    private static final double BASE_PRICE = 2.0;
    private static final int BASE_DAYS = 2;
    private static final double ADDITIONAL_PRICE = 1.5;

    @Override
    public double calculateAmount(MovieRental rental) {
        if (rental.getDaysRented() <= BASE_DAYS) {
            return BASE_PRICE;
        } else {
            return BASE_PRICE + (rental.getDaysRented() - BASE_DAYS) * ADDITIONAL_PRICE;
        }
    }

    @Override
    public int calculateFrequentRenterPoints(MovieRental rental) {
        return 1; // Regular movies always earn 1 point per rental
    }
}
