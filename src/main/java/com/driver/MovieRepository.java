package com.driver;

import com.driver.Director;
import com.driver.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    // store the movie object and diretdor in hashmap
    HashMap<String, List<Movie>> MovieListForDir = new HashMap<>();
    HashMap<String, Movie> MovieList = new HashMap<>();
    HashMap<String, Director> DirList = new HashMap<>();

    public void addMovie(Movie movie) {
        if (movie.getName() == null) return;
        String movieName = movie.getName();
        MovieList.put(movieName, movie);
    }

    public void addDirector(Director director) {
        if (director.getName() == null) return;
        String DirName = director.getName();
        DirList.put(DirName, director);
        return;
    }

    public ResponseEntity<String> addMovieDirectorPair(String movieName, String dirName) {
        // pair movie and director object based on thier names given as params
        if (movieName == null || dirName == null) return ResponseEntity.ok("movie name or director name is null");
        if (DirList.containsKey(dirName)) {
            if (MovieList.containsKey(movieName)) {
                // both movie and director present in db
                List list = MovieListForDir.getOrDefault(dirName, new ArrayList<>());
                list.add(MovieList.get(movieName));
                MovieListForDir.put(dirName, list);
                return ResponseEntity.ok("Successfully paired director and their movies");
            }
        }
        return ResponseEntity.ok("director or movie name not found");
    }

    public Movie getMovieByName(String name) {
        return MovieList.get(name);
    }

    public Director getDirectorByName(String dirName) {
        return DirList.get(dirName);
    }

    public List<Movie> getMoviesByDirectorName(String dirName) {
        return MovieListForDir.get(dirName);
    }

    public List<String> findAllMovies() {
        List<String> AllmovieNames = new ArrayList<>();
        for (Map.Entry<String, Movie> mapElement : MovieList.entrySet()) {
            AllmovieNames.add(mapElement.getKey());
        }
        return AllmovieNames;
    }

    public String deleteDirectorByName(String dirName) {
        if (DirList.containsKey(dirName)) {
            DirList.remove(dirName);
            if (MovieListForDir.containsKey(dirName)){
                MovieListForDir.remove(dirName);
            }
            return "Succesfully Deleted director's movie list";
        }
        return "Director Not Found";

    }

    public String deleteAllDirectors() {
        // have to delete whole directors from hashmap, and movies directed by them
        for (Map.Entry<String, List<Movie>> mapElement : MovieListForDir.entrySet()) {
            List<Movie> cinema = mapElement.getValue();
            // Traverse through arrayist of cinemas and and delete that movie in movielist

//            Iterator it = cinema.iterator();
//            while (it.hasNext()) {
//                Movie moviePresent = (Movie)it.next();
//                String movieName=moviePresent.getName();
//                MovieList.remove(movieName);

//        }
        for (Movie m : cinema) {
            String movieName=m.getName();
            MovieList.remove(movieName);
        }
    }
        MovieListForDir.clear();
        DirList.clear();
        return "Deleted successfully";

    }
}
