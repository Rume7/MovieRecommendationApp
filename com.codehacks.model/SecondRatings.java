/**
 * Write a description of SecondRatings here.
 * 
 * @author Rhume
 * @version 2.0 
 * @date 9 October, 2020
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRating = new FirstRatings();
        myMovies = firstRating.loadMovies(moviefile);
        myRaters = firstRating.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    } 
    
    private double getAverageByID(String id, int minimalRater) {
        // Note id represent movie ID
        int size = 0;
        double totalRating = 0.0;
        for (Rater eachRater : myRaters) {
            ArrayList<String> raterItemList = eachRater.getItemsRated();
            for (String anItem : raterItemList) {
                if (anItem.equals(id)) {
                    double rating = eachRater.getRating(anItem);
                    totalRating += rating;
                    size += 1;                  
                }                
            }                        
        }        
        if (size >= minimalRater) {
            return totalRating/size;
        } 
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRater) {
        HashMap<String, ArrayList<Double>> avgMap = new 
                        HashMap<String, ArrayList<Double>>();
                        
        for (Rater rater : myRaters) {
            ArrayList<String> movieIDList = rater.getItemsRated();
            for (String movieID : movieIDList) {
                double rating = rater.getRating(movieID);
                if (avgMap.containsKey(movieID)) {
                    ArrayList<Double> res = avgMap.get(movieID);
                    res.add(rating);
                    avgMap.put(movieID, res);
                } else {
                    ArrayList<Double> res = new ArrayList<>();
                    res.add(rating);
                    avgMap.put(movieID, res);
                }           
            }            
        }         
        ArrayList<Rating> freshList = new ArrayList<Rating>();
        for (String movieID : avgMap.keySet()) {
            double avgRatg = getAverageByID(movieID, minimalRater);
            Rating movieRating = new Rating(movieID, avgRatg);
            freshList.add(movieRating);
        }
        return freshList;
    }
    
    public String getTitle(String id) {
        for (Movie movie : myMovies){
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "ID not found";
    }
    
    public String getID(String title) {
        for (Movie movie : myMovies){
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }        
        return "NO SUCH TITLE";
    }
}