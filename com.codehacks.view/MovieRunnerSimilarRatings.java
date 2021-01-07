/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author Rhume 
 * @version 3.0
 * @date 12-18 October, 2020
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings() {
        //String fileName = "data/ratings.csv";
        FourthRatings tr = new FourthRatings();
        
        //System.out.println("read data for " + tr.getRaterSize() + " raters");
        //Print out the list of movies and their average ratings
        int minRater = 1;
        ArrayList<Rating> ans = tr.getAverageRatings(minRater);
        Collections.sort(ans);
        System.out.println("read data for " + ans.size() + " movies");                            
        int moviesFound = 0;
        for (Rating each : ans) {
            if (each.getValue() > 0.0) {
                moviesFound++;
                System.out.println(each.getValue() + "\t" + 
                MovieDatabase.getTitle(each.getItem())); 
            }
        }
        //System.out.println("found " + moviesFound + " movies");
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        Filter yearFilter = new YearAfterFilter(1980);
        Filter genreFilter = new GenreFilter("Romance");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);
        
        FourthRatings tr = new FourthRatings();
        int minimalRater = 1;
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(
                                minimalRater, allFilters);
        System.out.println("found " + ratings.size() + " movies");
        
        for(Rating each : ratings) {
            if(each.getValue() > 0 ) {
                System.out.println(each.getValue() + "\t" + 
                MovieDatabase.getYear(each.getItem()) + "\t" +
                MovieDatabase.getTitle(each.getItem()) + "\t" +
                MovieDatabase.getGenres(each.getItem())); 
            }
        }
    }
    
    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        int count = 0;
        String raterID = "71";
        int topNumOfSimilarRater = 20;
        int minimalRater = 5;
        ArrayList<Rating> ratingsGotten = fr.getSimilarRatings(raterID,
                            topNumOfSimilarRater, minimalRater);
        Collections.sort(ratingsGotten, Collections.reverseOrder());
        for (Rating rat : ratingsGotten) {
            count++;
            System.out.println(rat + " " + MovieDatabase.getTitle(rat.getItem()));
        }        
        //System.out.println("# of titles: " + count);
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        int count = 0;
        String raterID = "964";
        int topNumOfSimilarRater = 20;
        int minimalRater = 5;
        String genre = "Mystery";
        Filter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> ratingsGotten = fr.getSimilarRatingsByFilter(raterID, 
                topNumOfSimilarRater, minimalRater, genreFilter);
        Collections.sort(ratingsGotten, Collections.reverseOrder());
        for (Rating rat : ratingsGotten) {
            count++;
            System.out.println(rat + " " + MovieDatabase.getTitle(
                rat.getItem()) + " " + MovieDatabase.getGenres(rat.getItem()));
        } 
        //System.out.println("# of titles: " + count);
    }
    
    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        int count = 0;
        String raterID = "120";
        int topNumOfSimilarRater = 10;
        int minimalRater = 2;
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,"+
             "Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        Filter directorsFilter = new DirectorsFilter(directors);
        ArrayList<Rating> ratingsGotten = fr.getSimilarRatingsByFilter(raterID, 
            topNumOfSimilarRater, minimalRater, directorsFilter);
        Collections.sort(ratingsGotten, Collections.reverseOrder());
        for (Rating rat : ratingsGotten) {
            count++;
            System.out.println(rat + " " + 
                    MovieDatabase.getTitle(rat.getItem()) + " " + 
                    MovieDatabase.getGenres(rat.getItem()));
        } 
        //System.out.println("# of titles: " + count);
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        int count = 0;
        String raterID = "168";
        int topNumOfSimilarRater = 10;
        int minimalRater = 3;
        String genre = "Drama";
        int minTime = 80;
        int maxTime = 160;
        AllFilters allFilters = new AllFilters();
        Filter genreFilter = new GenreFilter(genre);        
        Filter minutesFilter = new MinuteFilter(minTime, maxTime);
        allFilters.addFilter(genreFilter);
        allFilters.addFilter(minutesFilter);
        
        ArrayList<Rating> ratingsGotten = fr.getSimilarRatingsByFilter(raterID,
                topNumOfSimilarRater, minimalRater, allFilters);
        Collections.sort(ratingsGotten, Collections.reverseOrder());
        for (Rating rat : ratingsGotten) {
            count++;
            System.out.println(rat + " " + MovieDatabase.getTitle(
                rat.getItem()) + " " + MovieDatabase.getGenres(rat.getItem()));
        } 
        //System.out.println("# of titles: " + count);
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        int count = 0;
        String raterID = "314";
        int topNumOfSimilarRater = 10;
        int minimalRater = 5;
        int year = 1975;
        int minTime = 70;
        int maxTime = 200;
        AllFilters allFilters = new AllFilters();
        Filter yearFilter = new YearAfterFilter(year);        
        Filter minutesFilter = new MinuteFilter(minTime, maxTime);
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(minutesFilter);
        
        ArrayList<Rating> ratingsGotten = fr.getSimilarRatingsByFilter(
                    raterID, topNumOfSimilarRater, minimalRater, allFilters);
        Collections.sort(ratingsGotten, Collections.reverseOrder());
        for (Rating rat : ratingsGotten) {
            count++;
            System.out.println(rat + " " + MovieDatabase.getTitle(
                rat.getItem()) + " " + MovieDatabase.getGenres(rat.getItem()));
        } 
        //System.out.println("# of titles: " + count);
    }
}
