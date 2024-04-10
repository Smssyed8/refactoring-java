package com.etrg.syed.assignment.movierental.repository;

import com.etrg.syed.assignment.movierental.model.Movie;
import com.etrg.syed.assignment.movierental.model.MovieTypeEnum;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Repo class for fetching movie
 * Can be enhanced with actual database and ORM if needed
 */
@Repository
public class MovieRepository {
    private final Map<String, Movie> movies = new HashMap<>();

    //simulating database operation using map, as there is no JpaRepository, we interact direct with map.
    public MovieRepository() {
        movies.put("F001", new Movie("You've Got Mail", MovieTypeEnum.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieTypeEnum.NEW_RELEASE));
        movies.put("F003", new Movie("Cars", MovieTypeEnum.CHILDREN));
        movies.put("F004", new Movie("Fast & Furious X", MovieTypeEnum.NEW_RELEASE));
    }

    public Optional<Movie> findById(String id) {
        return Optional.ofNullable(movies.get(id));
    }
}
