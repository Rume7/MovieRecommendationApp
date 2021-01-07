/**
 * RecommendationRunner here.
 * 
 * @author Rhume 
 * @version 1
 * @date Nov 6, 2020
 */
import java.util.*;

public class RecommendationRunner implements Recommender {
    
    public ArrayList<String> getItemsToRate() {
        Filter tFilter = new TrueFilter();
        ArrayList<String> fullMovieList = MovieDatabase.filterBy(tFilter);
        ArrayList<String> movieIDList = new ArrayList<>();
        Random random = new Random();
        int numberOfMovies = 20;
        
        for (int i = 0; i < numberOfMovies; i++) {
            int value = random.nextInt(fullMovieList.size());
            if (! movieIDList.contains(fullMovieList.get(value))) {
                movieIDList.add(fullMovieList.get(value));
            }
        }
        return movieIDList;
    }
    
    public void testing() {
        ArrayList<String> movieList = getItemsToRate();
        System.out.println(movieList);        
    }  
    
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fourth = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull");
        RaterDatabase.initialize("ratings");
        
        System.out.println("<p>Read data for " + 
               Integer.toString(RaterDatabase.size()) + " raters</p>");
        System.out.println("<p>Read data for " + 
               Integer.toString(MovieDatabase.size()) + " movies</p>");
        
        int numSimilarRaters = 20;
        int minimalRaters = 5;        
        ArrayList<Rating> similarRatings = fourth.getSimilarRatings(
                        webRaterID, numSimilarRaters, minimalRaters);
        
        int num = similarRatings.size();
        if (similarRatings.size() == 0 ){
            System.out.println("Not matching movies were found");
        } else {
            String header = ("<table> <tr> <th>Movie Title</th> " + 
                    "<th>Rating value</th> <th>Genres</th> </tr>");
            String body = "";
            for (Rating rating : similarRatings) {
                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) 
                        + "</td> </tr>" + Double.toString(rating.getValue())
                        + "</td> </tr>" + MovieDatabase.getGenres(rating.getItem())
                        + "</td></tr>";
            }
            System.out.println(header + body + "</table>");
        }
    }
    
    private void printRecommendationsFor1(String webRaterID) {
        MovieDatabase.initialize("");
        FourthRatings fourthRatings = new FourthRatings();
        TrueFilter trueFilter = new TrueFilter();
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        int count = 0;
        
        ArrayList<Rating> ratingList = fourthRatings.getSimilarRatingsByFilter(
            webRaterID, numSimilarRaters,  minimalRaters, trueFilter);
        
        String html_head = "<html><head><title>Movie result</title>"+
               "<style>" +
               "</style></head><body><table>";
               
        String result = "";
        for (Rating rating : ratingList) {
            result += "<tr>"  + 
            "<td>" + MovieDatabase.getTitle(rating.getItem()) + "</td>" +
            //"<td>" + MovieDatabase.getGenres(rating.getItem()) + "</td>" + 
            //"<td>" + MovieDatabase.getYear(rating.getItem()) + "</td>" +
            //"<td>" + MovieDatabase.getMinutes(rating.getItem()) + "</td>" +
            "</tr>";       
            count++;
            if (count == 10) {
                break;
            }
        }            
        String html_end = "</table></body></html>";
        System.out.println(html_head + result + html_end);        
    }
}
