/**
 * Write a description of EfficientRater here.
 * 
 * @author Rhume
 * @version 1.0
 * @date 10 October, 2020
 */
import java.util.*;

public class EfficientRater implements Rater{
    
    private String myID;
    // key in map is movieID, value is rating of the movie
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item, rating));
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);        
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if(myRatings.containsKey(item)) {
            return myRatings.get(item).getValue();
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String movieID : myRatings.keySet()) {
            list.add(movieID);
        }      
        return list;
    }
    
    public Set<String> getAllMovieIDs() {
        return myRatings.keySet();
    }
    
    public HashMap<String, Rating> getRatings() {
        return myRatings;
    }
}
