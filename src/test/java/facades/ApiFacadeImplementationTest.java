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
@Disabled
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
        
        simple = new MovieSimpleDTO("Alien", "The commercial vessel Nostromo receives a distress call from an unexplored planet. After searching for survivors, the crew heads home only to realize that a deadly bioform has joined them.", "Ridley Scott", "Horror,Sci-Fi", "Tom Skerritt,Sigourney Weaver,Veronica Cartwright,Harry Dean Stanton", 1979, "https://m.media-amazon.com/images/M/MV5BMmQ2MmU3NzktZjAxOC00ZDZhLTk4YzEtMDMyMzcxY2IwMDAyXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg");
        
        
        // MovieCountDTO(String title, int count)
        // MovieCache(String title, String response, int count, Date CachedDate)
        // RottenTomatoesDTO(String source, double viewerRating, double criticRating, int viewerNumReviews, int viewerMeter, int criticNumReviews, int criticMeter)
        // MetaCriticDTO(String Msource, int metacritic)
        // ImdbDTO(String Source, double Rating, int Votes)
        // MovieAllDTO(String title, String plot, String directors, String genres, String cast, int year, String poster, ImdbDTO imdb, RottenTomatoesDTO tomatoes, MetaCriticDTO metaCritic)
    }
    
    @AfterAll
    public static void tearDownClass() {
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
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error in tearDown FacadeExampleTest. Message: " + e.getMessage());
        } finally {
            em.close();
        }
    }


    /**
     * Test of simpleMovieData method, of class ApiFacadeImplementation.
     */
    @Test
    public void testSimpleMovieData() {
        System.out.println("simpleMovieData");
        String movieTitle = "";
        ApiFacadeImplementation instance = null;
        MovieSimpleDTO expResult = null;
        MovieSimpleDTO result = instance.simpleMovieData(movieTitle);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMovieCount method, of class ApiFacadeImplementation.
     */
    @Test
    public void testGetMovieCount() throws Exception {
        System.out.println("getMovieCount");
        String title = "";
        ApiFacadeImplementation instance = null;
        MovieCountDTO expResult = null;
        MovieCountDTO result = instance.getMovieCount(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allMovieData method, of class ApiFacadeImplementation.
     */
    @Test
    public void testAllMovieData() {
        System.out.println("allMovieData");
        String movieTitle = "";
        ApiFacadeImplementation instance = null;
        MovieAllDTO expResult = null;
        MovieAllDTO result = instance.allMovieData(movieTitle);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testData method, of class ApiFacadeImplementation.
     */
    @Test
    public void testTestData() {
        System.out.println("testData");
        ApiFacadeImplementation instance = null;
        boolean expResult = false;
        boolean result = instance.testData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
