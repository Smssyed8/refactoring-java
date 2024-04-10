/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.model;

public class MovieRental {
    private String movieId; // Could be changed to hold a reference to the Movie object directly
    private int daysRented;

    public MovieRental(String movieId, int daysRented) {
        this.movieId = movieId;
        this.daysRented = daysRented;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }
}
