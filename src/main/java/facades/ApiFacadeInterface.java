package facades;

import dto.MovieAllDTO;
import dto.MovieSimpleDTO;

/**
 *
 * @author Camilla
 */
public interface ApiFacadeInterface {
    
    public MovieSimpleDTO simpleMovieData(String movieTitle);
    
    public MovieAllDTO allMovieData(String movieTitle);
    
    public boolean testData();
    
}
