/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.strategy.impl;

import com.etrg.syed.assignment.movierental.model.MovieRental;
import com.etrg.syed.assignment.movierental.strategy.PricingStrategy;

public class NewReleasePricingStrategy implements PricingStrategy {
    private static final double PRICE_PER_DAY = 3.0;

    @Override
    public double calculateAmount(MovieRental rental) {
        return rental.getDaysRented() * PRICE_PER_DAY;
    }

    @Override
    public int calculateFrequentRenterPoints(MovieRental rental) {
        // Bonus point for a two day new release rental
        return rental.getDaysRented() > 1 ? 2 : 1;
    }
}
