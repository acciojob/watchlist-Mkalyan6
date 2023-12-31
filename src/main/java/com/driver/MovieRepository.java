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
        String movieName = movie.getName();
        MovieList.put(movieName, movie);
    }

    public void addDirector(Director director) {
        String DirName = director.getName();
        DirList.put(DirName, director);
    }

    public String addMovieDirectorPair(String movieName, String dirName) {
        // pair movie and director object based on thier names given as params
        if (movieName == null || dirName == null) return "movie name or director name is null";
        if (DirList.containsKey(dirName)) {
            if (MovieList.containsKey(movieName)) {
                // both movie and director present in db
                List<Movie> list = MovieListForDir.getOrDefault(dirName, new ArrayList<>());
                list.add(MovieList.get(movieName));
                MovieListForDir.put(dirName, list);
                return "Successfully paired director and their movies";
            }
        }
        return "director or movie name not found";
    }

    public Movie getMovieByName(String name) {
        return MovieList.get(name);
    }

    public Director getDirectorByName(String dirName) {
        return DirList.get(dirName);
    }

    public List<String> getMoviesByDirectorName(String dirName) {
        if(!MovieListForDir.containsKey(dirName))return new ArrayList<String>();
        List<Movie> moviesofDir=MovieListForDir.get(dirName);
        List<String>ans=new ArrayList<>();
        for(Movie m:moviesofDir){
            ans.add(m.getName());
        }
        return ans;
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
                //deleete all the movies directed by this director in movie hashmap
                List<Movie>DirectorMovies=MovieListForDir.get(dirName);
                for(Movie m:DirectorMovies){
                    String MovieName=m.getName();
                    MovieList.remove(MovieName);
                }
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
