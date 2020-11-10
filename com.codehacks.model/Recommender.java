/**
 * Write a description of Recommender here.
 * 
 * @author Rhume
 * @version 1
 * @date Nov 6, 2020
 */
import java.util.*;
public interface Recommender {
    
    public ArrayList<Movie> getItemsToRate();
    public void printRecommendationsFor();

}
