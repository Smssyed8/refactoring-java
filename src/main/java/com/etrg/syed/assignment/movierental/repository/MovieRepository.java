/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.repository;

import com.etrg.syed.assignment.movierental.model.Movie;
import com.etrg.syed.assignment.movierental.model.MovieTypeEnum;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MovieRepository {
    //Mock MovieRepository using Map, since there is no database connected at the moment.
    private final Map<String, Movie> movies = new HashMap<>();

    //simulating database operation using map, as there is no JpaRepository, we interact direct with map.
    public MovieRepository() {
        //populate the map with some movies
        movies.put("F001", new Movie("You've Got Mail", MovieTypeEnum.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieTypeEnum.NEW_RELEASE));
        movies.put("F003", new Movie("Cars", MovieTypeEnum.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieTypeEnum.NEW_RELEASE));
        //Can add more movies
    }

    //Implementing findById similar to Jpa. Can be moved away easily from map, wraps the result in an Optional for better null handling.
    public Optional<Movie> findById(String id) {
        return Optional.ofNullable(movies.get(id));
    }
}
