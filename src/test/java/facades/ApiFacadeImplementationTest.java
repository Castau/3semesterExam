/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.ImdbDTO;
import dto.MetaCriticDTO;
import dto.MovieAllDTO;
import dto.MovieCountDTO;
import dto.MovieSimpleDTO;
import dto.RottenTomatoesDTO;
import entities.MovieCache;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import utils.EMF_Creator;

/**
 *
 * @author Camilla
 */
//@Disabled
public class ApiFacadeImplementationTest {

    private static EntityManagerFactory emf;
    private static ApiFacadeImplementation facade;

    private static ImdbDTO imdb;
    private static MetaCriticDTO meta;
    private static MovieAllDTO all;
    private static MovieCountDTO count;
    private static MovieSimpleDTO simple;
    private static RottenTomatoesDTO rotten;
    private static MovieCache cache;

    public ApiFacadeImplementationTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = ApiFacadeImplementation.getApiFacade(emf);

        cache = new MovieCache("Alien", "{\"cast\":\"Tom Skerritt,Sigourney Weaver,Veronica Cartwright,Harry Dean Stanton\",\"directors\":\"Ridley Scott\",\"genres\":\"Horror,Sci-Fi\",\"imdb\":{\"rating\":8.5,\"source\":\"imdb\",\"votes\":502112},\"metaCritic\":{\"metacritic\":83,\"source\":\"metacritic\"},\"plot\":\"The commercial vessel Nostromo receives a distress call from an unexplored planet. After searching for survivors, the crew heads home only to realize that a deadly bioform has joined them.\",\"poster\":\"https://m.media-amazon.com/images/M/MV5BMmQ2MmU3NzktZjAxOC00ZDZhLTk4YzEtMDMyMzcxY2IwMDAyXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg\",\"title\":\"Alien\",\"tomatoes\":{\"criticMeter\":97,\"criticNumReviews\":100,\"criticRating\":9.0,\"source\":\"tomatoes\",\"viewerMeter\":94,\"viewerNumReviews\":453306,\"viewerRating\":3.9},\"year\":1979}", 1, new Date());
        simple = new MovieSimpleDTO("Alien", "The commercial vessel Nostromo receives a distress call from an unexplored planet. After searching for survivors, the crew heads home only to realize that a deadly bioform has joined them.", "Ridley Scott", "Horror,Sci-Fi", "Tom Skerritt,Sigourney Weaver,Veronica Cartwright,Harry Dean Stanton", 1979, "https://m.media-amazon.com/images/M/MV5BMmQ2MmU3NzktZjAxOC00ZDZhLTk4YzEtMDMyMzcxY2IwMDAyXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg");
        rotten = new RottenTomatoesDTO("tomatoes", 3.9, 9, 453306, 94, 100, 97);
        meta = new MetaCriticDTO("metacritic", 83);
        imdb = new ImdbDTO("imdb", 8.5, 502112);
        all = new MovieAllDTO("Alien", "The commercial vessel Nostromo receives a distress call from an unexplored planet. After searching for survivors, the crew heads home only to realize that a deadly bioform has joined them.", "Ridley Scott", "Horror,Sci-Fi", "Tom Skerritt,Sigourney Weaver,Veronica Cartwright,Harry Dean Stanton", 1979, "https://m.media-amazon.com/images/M/MV5BMmQ2MmU3NzktZjAxOC00ZDZhLTk4YzEtMDMyMzcxY2IwMDAyXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg", imdb, rotten, meta);
        count = new MovieCountDTO("Alien", 1);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cache);
        em.getTransaction().commit();
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("MovieCache.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error in tearDown FacadeExampleTest. Message: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Test
    public void testSimpleMovieData() {
        MovieSimpleDTO expResult = simple;
        MovieSimpleDTO result = facade.simpleMovieData("Alien");
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovieCount() throws Exception {
        MovieCountDTO expResult = count;
        MovieCountDTO result = facade.getMovieCount("Alien");
        assertEquals(expResult, result);
    }

    @Test
    public void testAllMovieData() {
        MovieAllDTO expResult = all;
        MovieAllDTO result = facade.allMovieData("Alien");
        System.out.println(result);
        assertEquals(expResult, result);
    }

}
