/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.strategy.impl;

import com.etrg.syed.assignment.movierental.model.MovieRental;
import com.etrg.syed.assignment.movierental.strategy.PricingStrategy;

import static com.etrg.syed.assignment.movierental.utilities.Constants.*;

public class ChildrensPricingStrategy implements PricingStrategy {

    @Override
    public double calculateAmount(MovieRental rental) {
        if (rental.getDaysRented() <= CHILDRENS_BASE_DAYS) {
            return CHILDRENS_BASE_PRICE;
        } else {
            return CHILDRENS_BASE_PRICE + (rental.getDaysRented() - CHILDRENS_BASE_DAYS) * CHILDRENS_ADDITIONAL_PRICE;
        }
    }

    @Override
    public int calculateFrequentRenterPoints(MovieRental rental) {
        return FREQUENT_RENTER_POINTS_BASE; // Children's movies always earn 1 point per rental
    }
}