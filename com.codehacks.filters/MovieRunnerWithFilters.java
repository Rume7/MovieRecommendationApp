/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author Rhume
 * @version 1.0 
 * @date 10 October, 2020
 */
import java.util.*;
public class MovieRunnerWithFilters {
    
    public void printAverageRatings() {
        //String fileName = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings();
        
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        
        // print out the list of movies and their average ratings
        int minRater = 35;
        ArrayList<Rating> ans = tr.getAverageRatings(minRater);
        ans.sort(null);
        System.out.println("read data for " + ans.size() + " movies");                            
        int moviesFound = 0;
        for (Rating each : ans) {
            if (each.getValue() > 0.0) {
                moviesFound++;
                System.out.println(each.getValue() + "\t" + 
                MovieDatabase.getTitle(each.getItem())); 
            }
        }
        System.out.println("found " + moviesFound + " movies");
    }
    
    public void printAverageRatingsByYear() {
        Filter yearFilter = new YearAfterFilter(2000);
        ThirdRatings tr = new ThirdRatings();
        int minimalRater = 20;
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(
                                minimalRater, yearFilter);
        System.out.println("found " + ratings.size() + " movies");
        int moviesFound = 0;
        for(Rating each : ratings) {
            if (each.getValue() > 0.0) {
                moviesFound++;
                System.out.println(each.getValue() + "\t" + 
                MovieDatabase.getYear(each.getItem()) + "\t" +
                MovieDatabase.getTitle(each.getItem())); 
            }
        }
        System.out.println("found " + moviesFound + " movies");
    }
    
    public void printAverageRatingsByGenre() {
        Filter gFilter = new GenreFilter("Comedy");
        ThirdRatings tr = new ThirdRatings();
        int minimalRater = 20;
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(
                                minimalRater, gFilter);
        System.out.println("found " + ratings.size() + " movies");
        int moviesFound = 0;
        for(Rating each : ratings) {
            if(each.getValue() > 0 ) {
                moviesFound++;
                System.out.println(each.getValue() + "\t" + 
                MovieDatabase.getTitle(each.getItem()) + "\t" +
                MovieDatabase.getGenres(each.getItem())); 
            }
        }
        System.out.println("found " + moviesFound + " movies");
    }
    
    public void printAverageRatingsByMinutes() {
        Filter minFilter = new MinuteFilter(105, 135);
        ThirdRatings tr = new ThirdRatings();
        int minimalRater = 5;
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(
                                minimalRater, minFilter);
        System.out.println("found " + ratings.size() + " movies");
        int moviesFound = 0;
        for(Rating each : ratings) {
            if(each.getValue() > 0 ) {
                moviesFound++;
                System.out.println(each.getValue() + "\t" + "Time: " +
                MovieDatabase.getMinutes(each.getItem()) + "\t" +
                MovieDatabase.getTitle(each.getItem())); 
            }
        }
        System.out.println("found " + moviesFound + " movies");
    } 
    
    public void printAverageRatingsByDirectors() {
        String dir = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter minFilter = new DirectorsFilter(dir);
        ThirdRatings tr = new ThirdRatings();
        int minimalRater = 4;
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(
                                minimalRater, minFilter);
        System.out.println("found " + ratings.size() + " movies");
        int moviesFound = 0;
        for(Rating each : ratings) {
            if(each.getValue() > 0 ) {
                moviesFound++;
                // System.out.println(each.getValue() + "\t" + 
                // MovieDatabase.getTitle(each.getItem()) + "\t" +
                // MovieDatabase.getDirector(each.getItem())); 
            }
        }
        //System.out.println("found " + moviesFound + " movies");
    } 
    
    public void printAverageRatingsByYearAfterAndGenre() {
        Filter yearFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);
        
        ThirdRatings tr = new ThirdRatings();
        int minimalRater = 8;
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(
                                minimalRater, allFilters);
        System.out.println("Found " + ratings.size() + " movies");
        
        int moviesFound = 0;
        for(Rating each : ratings) {
            if(each.getValue() > 0 ) {
                moviesFound++;
                // System.out.println(each.getValue() + "\t" + 
                // MovieDatabase.getYear(each.getItem()) + "\t" +
                // MovieDatabase.getTitle(each.getItem()) + "\t" +
                // MovieDatabase.getGenres(each.getItem())); 
            }
        }
        System.out.println("Found " + moviesFound + " movies");
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        String dir = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        Filter dirFilter = new DirectorsFilter(dir);
        Filter minuteFilter = new MinuteFilter(90, 180);
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(dirFilter);
        allFilters.addFilter(minuteFilter);
        
        ThirdRatings tr = new ThirdRatings();
        int minimalRater = 3;
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(
                                minimalRater, allFilters);
        System.out.println("Found " + ratings.size() + " movies");
        
        int moviesFound = 0;
        for(Rating each : ratings) {
            if(each.getValue() >= 0 ) {
                moviesFound++;
                System.out.println(each.getValue() + "\t" + "Time: " +
                MovieDatabase.getMinutes(each.getItem()) + "\t" +
                MovieDatabase.getTitle(each.getItem()) + "\t" +
                MovieDatabase.getDirector(each.getItem())); 
            }
        }
        System.out.println("Found " + moviesFound + " movies");
    }
}
