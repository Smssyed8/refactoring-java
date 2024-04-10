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

import static com.etrg.syed.assignment.movierental.utilities.Constants.*;
import static com.etrg.syed.assignment.movierental.utilities.MessageConstants.*;

@Service
public class RentalServiceImpl implements RentalService {
    private final MovieRepository movieRepository;

    @Autowired
    public RentalServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public String statement(Customer customer) {
        StringBuilder result = new StringBuilder(RENTAL_RECORD_FOR_).append(customer.getName()).append(":\n");
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        for (MovieRental rental : customer.getRentals()) {
            Movie movie = movieRepository.findById(rental.getMovieId())
                    .orElseThrow(() -> new IllegalArgumentException(MOVIE_NOT_FOUND));
            PricingStrategy strategy = PricingStrategyFactory.getStrategy(movie.getType());

            double thisAmount = strategy.calculateAmount(rental);
            frequentRenterPoints += strategy.calculateFrequentRenterPoints(rental);

            //TODO can use var here as improvement for amount, to avoid %.2f used to format to decimal 2 point.
            result.append(TAB).append(movie.getTitle()).append(TAB).append(String.format(STRING_TO_2POINT_DECIMAL, thisAmount)).append(LINE_BREAK);
            totalAmount += thisAmount;
        }
        //TODO can use var here as improvement for amount, to avoid %.2f used to format to decimal 2 point.
        result.append(AMOUNT_OWED).append(String.format(STRING_TO_2POINT_DECIMAL, totalAmount)).append(LINE_BREAK);
        result.append(YOU_EARNED).append(frequentRenterPoints).append(FREQUENT_RENTER_POINTS).append(LINE_BREAK);
        return result.toString();
    }
}
