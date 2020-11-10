/**
 * Write a description of FourthRating here.
 * 
 * @author Rhume  
 * @version 1.0
 * @date 12 October, 2020
 */
import java.util.*;
public class FourthRatings {
    
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile) {
        RaterDatabase.initialize(ratingsfile);       
        MovieDatabase.initialize("ratedmoviesfull.csv");
    }
    
    private double getAverageByID(String id, int minimalRater) {
        // Note id represent movie ID
        int size = 0;
        double totalRating = 0.0;
        for (Rater eachRater : RaterDatabase.getRaters()) {
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
        for (Rater rater : RaterDatabase.getRaters()) {
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
    
    private double dotProduct1(Rater me, Rater ur) {
        double product = 0.0;
        double midRating = 5.0;
        HashMap<String, Rating> allRating = me.getRatings();
        for (String myRatedMovie : allRating.keySet()) {
            if (ur.hasRating(myRatedMovie)) {
                double myRating = me.getRating(myRatedMovie);
                double urRating = ur.getRating(myRatedMovie);
                if (myRating > 0.0 && urRating > 0.0) {
                    product += (myRating - midRating) * (urRating - midRating);
                }
            }
        }
        return product;
    }
    
    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> items = MovieDatabase.filterBy(new TrueFilter());
        double product = 0.0;
        
        String meID = me.getID();
        String rID = r.getID();
        Rater meAfter = RaterDatabase.getRater(meID);
        Rater rAfter = RaterDatabase.getRater(rID);

        for (String item: items) {
            if (meAfter.hasRating(item) && rAfter.hasRating(item)) {
                double meScaledScore = meAfter.getRating(item) - 5.0;
                double rScaledScore = rAfter.getRating(item) - 5.0;
                double temp = meScaledScore * rScaledScore;
                product += temp;
            }
        }
        return product;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        Rater me = RaterDatabase.getRater(id);
        ArrayList<Rater> allRaters = RaterDatabase.getRaters();
        ArrayList<Rating> similarityRatings = new ArrayList<Rating>();        
        
        for (Rater eachRater : allRaters) {
            if (!eachRater.getID().equals(id)) {
                double productRating = dotProduct(me, eachRater);
                if (productRating > 0.0) {
                    similarityRatings.add(new Rating(eachRater.getID(), productRating)); 
                }
            }
        }
        Collections.sort(similarityRatings, Collections.reverseOrder());
        return similarityRatings;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, 
                    int numSimilarRaters, int minimalRaters) {
       ArrayList<Rating> answer = getSimilarRatingsByFilter(id, 
                numSimilarRaters, minimalRaters, new TrueFilter());
       return answer;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, 
        int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
       ArrayList<Rating> list = getSimilarities(id);
       ArrayList<Rating> answer = new ArrayList<Rating>();
       ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
       for(String movieID : movies){
           int count = 0;
           double sum = 0;
           for(int k = 0; k < numSimilarRaters; k++) {
               Rating r = list.get(k);
               String Rater_id = r.getItem();
               Rater simRater = RaterDatabase.getRater(Rater_id);
           
               if(simRater.hasRating(movieID)) {
                   count++;
                   double ratingSim = simRater.getRating(movieID);
                   double weight = r.getValue();
                   sum += ratingSim * weight;
               }
           }
           if(count >= minimalRaters) { 
               double w_average = sum/count;
               answer.add(new Rating(movieID, w_average));
           }         
       }
       return answer;
    }
}
