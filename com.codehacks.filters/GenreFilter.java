/**
 * Write a description of GenreFilter here.
 * 
 * @author Rhume
 * @version 1.0 
 * @date 10 October, 2020
 */
public class GenreFilter implements Filter {

    private String genre;
    
    public GenreFilter(String genre) {
        this.genre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(genre);
    }
}
