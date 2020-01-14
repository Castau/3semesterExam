package facades;

import com.google.gson.Gson;
import dto.MovieAllDTO;
import dto.MovieSimpleDTO;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author Camilla
 */

public class TestMain {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        ApiFacadeImplementation facade = ApiFacadeImplementation.getApiFacade(EMF);
        Gson gson = new Gson();
//        String map = facade.allApiData("Die%20Hard");
//        String map1 = facade.allApiData("Grease");
        MovieAllDTO movie = facade.allMovieData("Die%20Hard");
        
        MovieSimpleDTO simple = facade.simpleMovieData("Grease");
        
        Map<String, String> map = facade.allApiData("Die%20Hard");
        Map<String, String> map1 = facade.allApiData("Grease");
        
        System.out.println(gson.toJson(map.get("movieInfo")));
        System.out.println(gson.toJson(map1.get("movieInfo")));
        
        System.out.println("MOVIE");
        System.out.println(movie);
        System.out.println(movie.getImdb());
        System.out.println(movie.getMetaCritic());
        System.out.println(movie.getTomatoes());
        System.out.println("*******");
        System.out.println("simple");
        System.out.println(simple);
//        System.out.println(gson.toJson(map));
//        System.out.println(gson.toJson(map1));

    }
}
