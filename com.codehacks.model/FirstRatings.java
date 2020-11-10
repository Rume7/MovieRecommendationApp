/**
 * Write a description of FirstRatings here.
 * 
 * @author Rhume 
 * @version 1.0
 * @date October 9, 2020
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int mins = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            Movie movie = new Movie(id, title, year, genre, 
                            director, country, poster, mins);
            //Movie movie = new Movie(id, title, year, null, 
            //                null, null, null, 0);
            movieList.add(movie);
        }
        return movieList;
    }
    
    public void testLoadMovies() {
        String fileName = "data/ratedmoviesfull.csv";        
        ArrayList<Movie> local = loadMovies(fileName);
        
        // Print the number of movies
        System.out.println("# of movies: " + local.size());
        
        // Print each movie
        // for (Movie movie : local) {
            // System.out.println(movie);
        // }
        String movieGenre = "Comedy";
        int num = getMovieGenre(local, movieGenre);
        System.out.println("# of movies with " + movieGenre + 
                            " genre: " + num);
        int timeLength = 150;
        int freq = getMovieGreaterThanXMins(local, timeLength);   
        System.out.println("# movies longer than " + timeLength +
                            " mins: " + freq);
                            
        int numMovies = maxMoviesByDirector(local);
        System.out.println("Max num of movies: " + numMovies);
        
    }
    
    private int getMovieGenre(ArrayList<Movie> movieList, String genre) {
        int noOfGenres = 0;
        for (Movie movie : movieList) {
            if (movie.getGenres().contains(genre)) {
                noOfGenres++;
            }            
        }
        // System.out.println("# movies with " + genre + " genre: "
                            // + noOfGenres);
        return noOfGenres;                                
    }
    
    private int getMovieGreaterThanXMins(ArrayList<Movie>
                                    movieList, int duration) {
        int freq = 0;        
        for (Movie movie : movieList) {
            if (movie.getMinutes() > duration) {
                freq++;
            }            
        }
        // System.out.println("# movies longer than " + duration +
                            // " mins: " + freq);
        return freq;
    }   

    private int maxMoviesByDirector(ArrayList<Movie> movieList) {
        // Directors and number of movies they directed
        Map<String, Integer> directorMap = new HashMap<>();
        
        for (Movie movie : movieList) {
            String allDirectors = movie.getDirector(); 
            String[] directorArray = allDirectors.split(",");
           
            for (String director : directorArray){
                if (directorMap.containsKey(director)){
                   int value = directorMap.get(director);
                   directorMap.put(director, value+1);
                } else {
                   directorMap.put(director, 1);
                }
            }
        }
        // Maximum number of movies by any director
        int maxNumMovies = 0;
        for (Integer value : directorMap.values()) {
            if (maxNumMovies < value) {
                maxNumMovies = value;
            }
        }
        //System.out.println("Max num of movies: " + maxNumMovies);
        
        for (String dirName : directorMap.keySet()) {
            if (directorMap.get(dirName) == maxNumMovies) {
                System.out.println("Director : " + dirName);
            }
        }
        return maxNumMovies;
    }
    
    private int maxMoviesByADirector(ArrayList<Movie> movieList,
                                     String director) {
        // Directors and number of movies they directed
        int numOfMoviesDirected = 0;
        for (Movie movie : movieList) {
            String allDirectors = movie.getDirector(); 
            String[] directors = allDirectors.split("\\s+");
           
            for (String aDirector : directors){
                if (aDirector.equals(director)) {
                    numOfMoviesDirected++;
                }
            }
        }
        return numOfMoviesDirected;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {     
        ArrayList<Rater> raterList = new ArrayList<Rater>();        
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            Double movieRating = Double.parseDouble(record.get("rating"));
            
            Rater rater = new EfficientRater(raterID); // note                         
            if (!containsRater(raterList, rater)) {
                rater.addRating(movieID, movieRating); 
                raterList.add(rater);
            } else {
                int indexOfRater = indexOf(raterList, rater);
                rater = raterList.get(indexOfRater);
                rater.addRating(movieID, movieRating);
                raterList.set(indexOfRater, rater);
            }
        }
        return raterList;
    }
    
    private int indexOf(ArrayList<Rater> raterList, Rater rater) {
        for (int index = 0; index < raterList.size(); index++) {
            if (raterList.get(index).getID().equals(rater.getID())){
                return index;
            }
        }
        return -1;
    }
    
    private boolean containsRater(ArrayList<Rater> ratersList, 
                                    Rater rater) {
        for (Rater aRater : ratersList) {
            if(aRater.getID().equals(rater.getID())) {
                return true;
            }
        }
        return false;
    }
    
    public void testLoadRaters() {
        String fileName = "data/ratings.csv";
        //String fileName = "data/ratings_short.csv";
        ArrayList<Rater> local = loadRaters(fileName);
        
        System.out.println("# of raters: " + local.size());
        /**
        for (Rater rater : local) {
            String result = rater.getID() + " has " + 
                 rater.numRatings() + " ratings. ";
            for (String movieID : rater.getItemsRated()) {
                result += movieID + " "+ rater.getRating(movieID) + " ";
            }
            System.out.println(result.trim());
            result = "";
        }        */
        String raterID = "193";
        int numOfRatings = getRatingByID(local, raterID);
        System.out.println("Rater " + raterID + " has " + 
                            numOfRatings + " ratings.");
                            
        int max = maxRatingsByAnyRater(local);                    
        System.out.println("Maximum rating of all raters " + max);
        
        int uniqueMovies = getNumberOfRatedMovies(fileName);
        System.out.println("# of movies: " + uniqueMovies);
    }
    
    private int getRatingByID(ArrayList<Rater> local, String rater_id) {
        int freq = 0;
        for (Rater rater : local) {
            String result = rater.getID();
            if (rater.getID().equals(rater_id)) {
                return rater.numRatings();
            }
        }
        return -1;
    }
    
    public int maxRatingsByAnyRater(ArrayList<Rater> raterList) {
        int max = 0;
        for (Rater rater : raterList) {
            int value = getRatingByID(raterList, rater.getID());
            if (max < value) {
                max = value;
            }
        }
        return max;
    }
    
    public int numRatersHavingMaxRating(ArrayList<Rater> raterList) {
        int freq = 0;
        int max = maxRatingsByAnyRater(raterList);
        for (Rater rater : raterList) {
            if (max == rater.numRatings()) {
                freq++;
            }
        }
        return freq;
    }
    
    public int numOfRatingsByMovieID(ArrayList<Rater> raterList,
                                        String movieID) {
        int num = 0;
        for (Rater rater : raterList) {            
            for (String item : rater.getItemsRated()) {
                if (item.equals(movieID)) {
                    num++;
                }
            }
        }
        return num;
    }
    
    public int getNumberOfRatedMovies(String filename) {
        ArrayList<String> moviesList = new ArrayList<String>();        
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            Double movieRating = Double.parseDouble(record.get("rating"));
         
            if (!containsMovieID(moviesList, movieID)) {
                moviesList.add(movieID);
            }
        }
        return moviesList.size();
    }
    
    private boolean containsMovieID(ArrayList<String> movieList,
                                                String movieID) {
        for(String eachMovieID : movieList) {
            if(eachMovieID.equals(movieID)) {
                return true;
            }
        }
        return false;        
    }
}
