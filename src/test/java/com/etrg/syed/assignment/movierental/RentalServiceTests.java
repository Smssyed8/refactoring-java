package com.etrg.syed.assignment.movierental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.etrg.syed.assignment.movierental.exception.customexceptions.InvalidRentalPeriodException;
import com.etrg.syed.assignment.movierental.exception.customexceptions.MovieNotFoundException;
import com.etrg.syed.assignment.movierental.model.Customer;
import com.etrg.syed.assignment.movierental.model.Movie;
import com.etrg.syed.assignment.movierental.model.MovieRental;
import com.etrg.syed.assignment.movierental.model.MovieTypeEnum;
import com.etrg.syed.assignment.movierental.repository.MovieRepository;
import com.etrg.syed.assignment.movierental.service.impl.RentalServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RentalServiceTests {

  @Mock private MovieRepository movieRepository;

  private RentalServiceImpl rentalService; // Initializing in setUp() instead of using @InjectMocks

  @BeforeEach
  void setUp() {
    // Manual instantiation and injection
    rentalService = new RentalServiceImpl(movieRepository);

    // Mock behavior setup for known movie IDs
    when(movieRepository.findById("F001"))
            .thenReturn(Optional.of(new Movie("You've Got Mail", MovieTypeEnum.REGULAR)));
    when(movieRepository.findById("F002"))
            .thenReturn(Optional.of(new Movie("Matrix", MovieTypeEnum.REGULAR)));
    when(movieRepository.findById("F003"))
            .thenReturn(Optional.of(new Movie("Cars", MovieTypeEnum.CHILDREN)));
    when(movieRepository.findById("F004"))
            .thenReturn(Optional.of(new Movie("Fast & Furious X", MovieTypeEnum.NEW_RELEASE)));
  }

  @Test
  @DisplayName("regular movies only")
  void calculateRegularMovieRentalCost() {
    Customer customer =
            new Customer(
                    "C. U. Stomer",
                    Arrays.asList(
                            new MovieRental("F001", 3), // Regular movie for 3 days
                            new MovieRental("F002", 1) // Another Regular movie for 1 day
                    ));

    // Expected statement
    String expectedStatement =
            """
            Rental Record for C. U. Stomer
            \tYou've Got Mail\t3.5
            \tMatrix\t2.0
            Amount owed is 5.5
            You earned 2 frequent points
            """;

    String statement = rentalService.statement(customer);

    // Assert
    assertEquals(expectedStatement.trim(), statement.trim());
  }

  @Test
  @DisplayName("Calculate statement for a mix of regular and children's movies")
  void testStatementForMixedRentals() {
    Customer customer =
            new Customer(
                    "C. U. Stomer",
                    Arrays.asList(
                            new MovieRental("F001", 3), // Regular movie for 3 days
                            new MovieRental("F003", 1) // Children's movie for 1 day
                    ));

    String expected =
            """
            Rental Record for C. U. Stomer
            \tYou've Got Mail\t3.5
            \tCars\t1.5
            Amount owed is 5.0
            You earned 2 frequent points
            """;
    String result = rentalService.statement(customer);

    assertEquals(expected.trim(), result.trim());
  }

  @Test
  @DisplayName("Calculate statement for new release movie rented for more than one day")
  void testStatementForNewReleaseMultipleDays() {
    Customer customer =
            new Customer(
                    "C. U. Stomer",
                    List.of(
                            new MovieRental("F004", 3) // New release movie for 3 days
                    ));

    String expected =
            """
            Rental Record for C. U. Stomer
            \tFast & Furious X\t9.0
            Amount owed is 9.0
            You earned 2 frequent points
            """;
    String result = rentalService.statement(customer);

    assertEquals(expected.trim(), result.trim());
  }

  @Test
  @DisplayName("Expect MovieNotFoundException for unknown movie ID")
  void whenMovieIdNotFound_thenThrowMovieNotFoundException() {
    Customer customer = new Customer("C. U. Stomer", List.of(new MovieRental("UNKNOWN_ID", 3)));

    // Setup to simulate that the movie does not exist in the repository
    when(movieRepository.findById("UNKNOWN_ID")).thenReturn(Optional.empty());

    // Verify that MovieNotFoundException is thrown
    assertThrows(MovieNotFoundException.class, () -> rentalService.statement(customer));
  }

  @Test
  @DisplayName("Expect InvalidRentalPeriodException for negative rental days")
  void whenRentalDaysAreNegative_thenThrowInvalidRentalPeriodException() {
    Customer customer = new Customer("C. U. Stomer", List.of(new MovieRental("F001", -1)));
    when(movieRepository.findById("F001"))
            .thenReturn(Optional.of(new Movie("You've Got Mail", MovieTypeEnum.REGULAR)));

    assertThrows(InvalidRentalPeriodException.class, () -> rentalService.statement(customer));
  }

  @Test
  @DisplayName("Handle null Customer object gracefully")
  void whenCustomerIsNull_thenHandleGracefully() {
    assertThrows(NullPointerException.class, () -> rentalService.statement(null));
  }

  @Test
  @DisplayName("Handle null rentals list within Customer object")
  void whenRentalsListIsNull_thenHandleGracefully() {
    Customer customer = new Customer("C. U. Stomer", null);
    assertThrows(NullPointerException.class, () -> rentalService.statement(customer));
  }

  @Test
  @DisplayName("Calculate statement with a mix of all movie types")
  void testStatementWithMixedMovieTypes() {
    Customer customer =
            new Customer(
                    "C. U. Stomer",
                    Arrays.asList(
                            new MovieRental("F001", 3), // Regular movie for 3 days
                            new MovieRental("F002", 1), // Regular movie for 1 day
                            new MovieRental("F003", 4), // Children's movie for 4 days
                            new MovieRental("F004", 2) // New release movie for 2 days
                    ));

    String expected =
            """
            Rental Record for C. U. Stomer
            \tYou've Got Mail\t3.5
            \tMatrix\t2.0
            \tCars\t3.0
            \tFast & Furious X\t6.0
            Amount owed is 14.5
            You earned 4 frequent points
            """;
    String result = rentalService.statement(customer);

    assertEquals(expected.trim(), result.trim());
  }
}
