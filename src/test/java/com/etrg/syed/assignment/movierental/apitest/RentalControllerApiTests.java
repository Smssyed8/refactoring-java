package com.etrg.syed.assignment.movierental.apitest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.etrg.syed.assignment.movierental.model.Customer;
import com.etrg.syed.assignment.movierental.model.Movie;
import com.etrg.syed.assignment.movierental.model.MovieTypeEnum;
import com.etrg.syed.assignment.movierental.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class RentalControllerApiTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private RentalService rentalService;

  @BeforeEach
  void setUp() {
    Movie regularMovie = new Movie("You've Got Mail", MovieTypeEnum.REGULAR);
    Movie newReleaseMovie = new Movie("Fast & Furious X", MovieTypeEnum.NEW_RELEASE);
    when(rentalService.findMovieById("F001")).thenReturn(regularMovie);
    when(rentalService.findMovieById("F004")).thenReturn(newReleaseMovie);

    when(rentalService.statement(any(Customer.class)))
        .thenReturn(
            """
            Rental Record for C. U. Stomer
            \tYou've Got Mail\t3.5
            \tFast & Furious X\t9.0
            Amount owed is 12.5
            You earned 3 frequent points
            """);
  }

  @Test
  @DisplayName("Get Movie by ID - Success")
  void whenValidMovieId_thenMovieShouldBeReturned() throws Exception {
    mockMvc
        .perform(get("/rentals/movies/F001"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("You've Got Mail"))
        .andExpect(jsonPath("$.type").value(MovieTypeEnum.REGULAR.name()));
  }

  @Test
  @DisplayName("Generate Rental Statement - Success")
  void whenValidCustomer_thenStatementShouldBeReturned() throws Exception {
    mockMvc
        .perform(
            post("/rentals/statement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                          {
                            "name": "C. U. Stomer",
                            "rentals": [
                              { "movieId": "F001", "daysRented": 3 },
                              { "movieId": "F004", "daysRented": 2 }
                            ]
                          }
                          """))
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$")
                .value(
                    """
                Rental Record for C. U. Stomer
                \tYou've Got Mail\t3.5
                \tFast & Furious X\t9.0
                Amount owed is 12.5
                You earned 3 frequent points"""));
  }
}
