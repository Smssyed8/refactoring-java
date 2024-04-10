/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.strategy.impl;

import com.etrg.syed.assignment.movierental.model.MovieRental;

import static com.etrg.syed.assignment.movierental.utilities.Constants.NEW_RELEASE_PRICE_PER_DAY;
import static com.etrg.syed.assignment.movierental.utilities.Constants.BONUS_FREQUENT_RENTER_POINTS;
import static com.etrg.syed.assignment.movierental.utilities.Constants.FREQUENT_RENTER_POINTS_BASE;

/**
 * Pricing Strategy for Calculating amount and getting FrequentRenterPoints for New Release category
 */
public final class NewReleasePricingStrategy implements PricingStrategy {

    @Override
    public double calculateAmount(MovieRental rental) {
        return rental.daysRented() * NEW_RELEASE_PRICE_PER_DAY;
    }

    @Override
    public int calculateFrequentRenterPoints(MovieRental rental) {
        // Bonus point for a two day new release rental
        return rental.daysRented() > 2 ? BONUS_FREQUENT_RENTER_POINTS : FREQUENT_RENTER_POINTS_BASE;
    }
}
