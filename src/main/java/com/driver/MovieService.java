package com.driver;

import com.driver.Director;
import com.driver.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity addMovieDirectorPair(String movieName, String dirName) {
        return ResponseEntity.ok(Repository.addMovieDirectorPair(movieName,dirName));
    }

    public Movie getMovieByName(String name) {
        return Repository.getMovieByName(name);
    }

    public Object getDirectorByName(String dirName) {
        return Repository.getDirectorByName(dirName);
    }

    public ResponseEntity getMoviesByDirectorName(String dirName) {
        return ResponseEntity.ok(Repository.getMoviesByDirectorName(dirName));
    }

    public ResponseEntity findAllMovies() {
        return ResponseEntity.ok(Repository.findAllMovies());
    }

    public ResponseEntity deleteDirectorByName(String dirName) {
        return ResponseEntity.ok(Repository.deleteDirectorByName(dirName));
    }

    public ResponseEntity deleteAllDirectors() {
        return ResponseEntity.ok(Repository.deleteAllDirectors());
    }
}
