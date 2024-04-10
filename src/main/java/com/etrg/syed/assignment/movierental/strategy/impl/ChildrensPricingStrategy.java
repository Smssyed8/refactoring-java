/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.strategy.impl;

import com.etrg.syed.assignment.movierental.model.MovieRental;

import static com.etrg.syed.assignment.movierental.utilities.Constants.CHILDRENS_BASE_DAYS;
import static com.etrg.syed.assignment.movierental.utilities.Constants.CHILDRENS_BASE_PRICE;
import static com.etrg.syed.assignment.movierental.utilities.Constants.CHILDRENS_ADDITIONAL_PRICE;
import static com.etrg.syed.assignment.movierental.utilities.Constants.FREQUENT_RENTER_POINTS_BASE;

/**
 * Pricing Strategy for Calculating amount and getting FrequentRenterPoints for Children category
 */
public final class ChildrensPricingStrategy implements PricingStrategy {

    @Override
    public double calculateAmount(MovieRental rental) {
        if (rental.daysRented() <= CHILDRENS_BASE_DAYS) {
            return CHILDRENS_BASE_PRICE;
        } else {
            return CHILDRENS_BASE_PRICE + (rental.daysRented() - CHILDRENS_BASE_DAYS) * CHILDRENS_ADDITIONAL_PRICE;
        }
    }

    @Override
    public int calculateFrequentRenterPoints(MovieRental rental) {
        return FREQUENT_RENTER_POINTS_BASE; // children's movies earn 1 point per rental
    }
}