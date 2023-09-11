package com.driver;

import com.driver.Director;
import com.driver.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")

public class MovieController {
    @Autowired
    MovieService Service;
    @PostMapping ("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
                  Service.addMovie(movie);
                  return ResponseEntity.ok("Success");
    }
    @PostMapping ("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
         Service.addDirector(director);
         return ResponseEntity.ok("Success");
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName ,@RequestParam("DirName") String DirName) {
         return ResponseEntity.ok(Service.addMovieDirectorPair(movieName, DirName));
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
          return ResponseEntity.ok(Service.getMovieByName(name));
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String dirName){
        return ResponseEntity.ok(Service.getDirectorByName(dirName));

    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director")String dirName){
        return ResponseEntity.ok(Service.getMoviesByDirectorName(dirName));
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        return ResponseEntity.ok(Service.findAllMovies());
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("q") String dirName){
        return ResponseEntity.ok(Service.deleteDirectorByName(dirName));
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
               return ResponseEntity.ok(Service.deleteAllDirectors());
    }





}
