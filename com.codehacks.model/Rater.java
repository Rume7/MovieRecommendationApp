/**
 * Write a description of Rater here.
 * 
 * @author Rhume 
 * @version 1.0
 * @date 10 October, 2020
 */
import java.util.*;
public interface Rater {
    
    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    public String getID();
    public double getRating(String item);
    public int numRatings();
    public ArrayList<String> getItemsRated();
    public HashMap<String, Rating> getRatings();

}
