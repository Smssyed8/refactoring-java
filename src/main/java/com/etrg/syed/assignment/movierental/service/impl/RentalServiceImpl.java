package com.etrg.syed.assignment.movierental.service.impl;

import com.etrg.syed.assignment.movierental.exception.customexceptions.MovieNotFoundException;
import com.etrg.syed.assignment.movierental.exception.customexceptions.InvalidRentalPeriodException;
import com.etrg.syed.assignment.movierental.model.Customer;
import com.etrg.syed.assignment.movierental.model.Movie;
import com.etrg.syed.assignment.movierental.model.MovieRental;
import com.etrg.syed.assignment.movierental.repository.MovieRepository;
import com.etrg.syed.assignment.movierental.service.RentalService;
import com.etrg.syed.assignment.movierental.strategy.impl.PricingStrategy;
import com.etrg.syed.assignment.movierental.strategy.PricingStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.etrg.syed.assignment.movierental.utilities.Constants.LINE_BREAK;
import static com.etrg.syed.assignment.movierental.utilities.Constants.TAB;
import static com.etrg.syed.assignment.movierental.utilities.MessageConstants.*;

/**
 * Service Impl for Calculating total amount statement and find Movie By Id
 */
@Service
public class RentalServiceImpl implements RentalService {
    private final MovieRepository movieRepository;

    @Autowired
    public RentalServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public String statement(Customer customer) {
        StringBuilder result = new StringBuilder(RENTAL_RECORD_FOR).append(customer.name()).append(LINE_BREAK);
        var totalAmount = 0.0;
        int frequentRenterPoints = 0;

        if (customer.rentals() == null) {
            throw new NullPointerException(CUSTOMER_AND_RENTALS_LIST_CANNOT_BE_NULL);
        }

        for (MovieRental rental : customer.rentals()) {
            if (rental.daysRented() < 0) {
                throw new InvalidRentalPeriodException(RENTAL_PERIOD_CANNOT_BE_NEGATIVE);
            }

            Movie movie = findMovieById(rental.movieId());
            PricingStrategy strategy = PricingStrategyFactory.getStrategy(movie.type());

            var thisAmount = strategy.calculateAmount(rental);
            frequentRenterPoints += strategy.calculateFrequentRenterPoints(rental);

            result.append(TAB).append(movie.title()).append(TAB).append(thisAmount).append(LINE_BREAK);
            totalAmount += thisAmount;
        }

        result.append(AMOUNT_OWED).append(totalAmount).append(LINE_BREAK);
        result.append(YOU_EARNED).append(frequentRenterPoints).append(FREQUENT_RENTER_POINTS).append(LINE_BREAK);
        return result.toString();
    }

    @Override
    public Movie findMovieById(String movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with ID " + movieId + " not found."));
    }
}
