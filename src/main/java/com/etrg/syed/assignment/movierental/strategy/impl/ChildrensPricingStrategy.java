/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.strategy.impl;

import com.etrg.syed.assignment.movierental.model.MovieRental;
import com.etrg.syed.assignment.movierental.strategy.PricingStrategy;

public class ChildrensPricingStrategy implements PricingStrategy {
    private static final double BASE_PRICE = 1.5;
    private static final int BASE_DAYS = 3;
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
        return 1; // Children's movies always earn 1 point per rental
    }
}