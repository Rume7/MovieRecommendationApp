/**
 * Write a description of DirectorsFilter here.
 * 
 * @author Rhume
 * @version 1.0 
 * @date 11 October, 2020
 */
public class DirectorsFilter implements Filter {

    private String movieDirectors;
    
    public DirectorsFilter(String directors) {
        this.movieDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {        
        String director = MovieDatabase.getDirector(id);
        return movieDirectors.contains(director);
    }
}
