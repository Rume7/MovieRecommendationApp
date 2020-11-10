/**
 * Write a description of ThirdRatings here.
 * 
 * @author Rhume 
 * @version 2.0
 * @date 10 October, 2020
 */
import java.util.*;
public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FirstRatings firstRating = new FirstRatings();        
        myRaters = firstRating.loadRaters(ratingsfile);
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
        /**
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
        } */           
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> filteredList = new ArrayList<Rating>();
        for (String movieID : movies) {
            double avgRatg = getAverageByID(movieID, minimalRater);
            Rating movieRating = new Rating(movieID, avgRatg);
            filteredList.add(movieRating);
        }
        return filteredList;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, 
                                Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        
        for (String id : movieIDs) {
            double avg = getAverageByID(id, minimalRaters);
            ratings.add(new Rating(id, avg));
        }
        Collections.sort(ratings);
        return ratings;
    }
}
