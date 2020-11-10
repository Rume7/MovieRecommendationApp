/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author Rhume 
 * @version 2.0
 * @date 9 October, 2020
 */
import java.util.*;
public class MovieRunnerAverage {
    
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings();
        
        System.out.println("# of movies: " + sr.getMovieSize());
        System.out.println("# of raters: " + sr.getRaterSize());
        
        // print out the list of movies and their average ratings
        int count = 0;
        int minRater = 12;
        ArrayList<Rating> ans = sr.getAverageRatings(minRater);
        ans.sort(null);
        for (Rating each : ans) {
            if (each.getValue() > 0.0) {
                System.out.println(each.getValue() + " " + 
                sr.getTitle(each.getItem()));// + each.getItem());
                count++;
            }
        }
        System.out.println("No of movies with 1 as minRater: " + 
                                    count);
    }

    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings();
        System.out.println("Movie size = " + sr.getMovieSize());
        System.out.println("Rater size = " + sr.getRaterSize());
        
        // print out the average rating from a single movie title
        
        int minRater = 1;
        String movieTitle = "Vacation";
        ArrayList<Rating> ans = sr.getAverageRatings(minRater);
        for (Rating each : ans) {
            if(each.getItem().equals(movieTitle)) {
                System.out.println(each.getValue());
            }
        }
        System.out.println("Done");
    }
}
