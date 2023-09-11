package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository Repository;
    public void addMovie(Movie movie) {
        Repository.addMovie(movie);
    }

    public void addDirector(Director director) {
        Repository.addDirector(director);
    }

    public String addMovieDirectorPair(String movieName, String dirName) {
        return Repository.addMovieDirectorPair(movieName,dirName);
    }

    public Movie getMovieByName(String name) {
        return Repository.getMovieByName(name);
    }

    public Director getDirectorByName(String dirName) {
        return Repository.getDirectorByName(dirName);
    }

    public List<Movie> getMoviesByDirectorName(String dirName) {
        return Repository.getMoviesByDirectorName(dirName);
    }

    public List<String> findAllMovies() {
        return Repository.findAllMovies();
    }

    public String deleteDirectorByName(String dirName) {
        return Repository.deleteDirectorByName(dirName);
    }

    public String deleteAllDirectors() {
        return Repository.deleteAllDirectors();
    }
}
