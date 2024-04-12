package com.etrg.syed.assignment.movierental.strategy.impl;

import com.etrg.syed.assignment.movierental.model.MovieRental;

/**
 * Defines a contract for movie rental pricing strategies, enabling calculation of rental charges
 * and frequent renter points. Permits only specific implementations for different movie types.
 * Strategy pattern is used touching upon some SOLID principles
 */
public sealed interface PricingStrategy
    permits RegularPricingStrategy, NewReleasePricingStrategy, ChildrensPricingStrategy {
  double calculateAmount(MovieRental rental);

  int calculateFrequentRenterPoints(MovieRental rental);
}
