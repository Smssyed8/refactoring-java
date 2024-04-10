/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.model;

public class Movie {
    private String title;
    private MovieTypeEnum type;

    public Movie(String title, MovieTypeEnum type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieTypeEnum getType() {
        return type;
    }

    public void setType(MovieTypeEnum type) {
        this.type = type;
    }
}