/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.strategy.impl;

import com.etrg.syed.assignment.movierental.model.MovieRental;
import com.etrg.syed.assignment.movierental.strategy.PricingStrategy;

import static com.etrg.syed.assignment.movierental.utilities.Constants.NEW_RELEASE_PRICE_PER_DAY;
import static com.etrg.syed.assignment.movierental.utilities.Constants.BONUS_FREQUENT_RENTER_POINTS;
import static com.etrg.syed.assignment.movierental.utilities.Constants.FREQUENT_RENTER_POINTS_BASE;

public class NewReleasePricingStrategy implements PricingStrategy {

    @Override
    public double calculateAmount(MovieRental rental) {
        return rental.getDaysRented() * NEW_RELEASE_PRICE_PER_DAY;
    }

    @Override
    public int calculateFrequentRenterPoints(MovieRental rental) {
        // Bonus point for a two day new release rental
        return rental.getDaysRented() > 2 ? BONUS_FREQUENT_RENTER_POINTS : FREQUENT_RENTER_POINTS_BASE;
    }
}
