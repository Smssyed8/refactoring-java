/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.service.impl;

import com.etrg.syed.assignment.movierental.model.Customer;
import com.etrg.syed.assignment.movierental.model.Movie;
import com.etrg.syed.assignment.movierental.model.MovieRental;
import com.etrg.syed.assignment.movierental.repository.MovieRepository;
import com.etrg.syed.assignment.movierental.service.RentalService;
import com.etrg.syed.assignment.movierental.strategy.PricingStrategy;
import com.etrg.syed.assignment.movierental.strategy.PricingStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceImpl implements RentalService {
    private final MovieRepository movieRepository;

    @Autowired
    public RentalServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public String statement(Customer customer) {
        //TODO move constants
        StringBuilder result = new StringBuilder("Rental Record for ").append(customer.getName()).append(":\n");
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        for (MovieRental rental : customer.getRentals()) {
            Movie movie = movieRepository.findById(rental.getMovieId())
                    .orElseThrow(() -> new IllegalArgumentException("Movie ID not found"));
            PricingStrategy strategy = PricingStrategyFactory.getStrategy(movie.getType());

            double thisAmount = strategy.calculateAmount(rental);
            frequentRenterPoints += strategy.calculateFrequentRenterPoints(rental);

            //TODO I think I can use var here as improvement for amount, to avoid %.2f used to format to decimal 2 point.
            result.append("\t").append(movie.getTitle()).append("\t").append(String.format("%.2f", thisAmount)).append("\n");
            totalAmount += thisAmount;
        }
        //TODO I think I can use var here as improvement for amount, to avoid %.2f used to format to decimal 2 point.
        result.append("Amount owed is ").append(String.format("%.2f", totalAmount)).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");
        return result.toString();
    }
}
