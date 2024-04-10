/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.model;

import java.util.List;

/**
 * Customer
 * @param name
 * @param rentals
 */
public record Customer (String name, List<MovieRental> rentals) {}
