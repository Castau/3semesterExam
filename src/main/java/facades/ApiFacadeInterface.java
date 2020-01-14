package facades;

import dto.MovieAllDTO;
import dto.MovieCountDTO;
import dto.MovieSimpleDTO;
import errorhandling.NotFoundException;

/**
 *
 * @author Camilla
 */
public interface ApiFacadeInterface {
    
    public MovieSimpleDTO simpleMovieData(String movieTitle);
    
    public MovieAllDTO allMovieData(String movieTitle);
    
    public MovieCountDTO getMovieCount(String title)  throws NotFoundException;
    
    public boolean testData();
    
}
