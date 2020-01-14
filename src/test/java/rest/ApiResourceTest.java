package rest;

import dto.ImdbDTO;
import dto.MetaCriticDTO;
import dto.MovieAllDTO;
import dto.MovieCountDTO;
import dto.MovieSimpleDTO;
import dto.RottenTomatoesDTO;
import entities.MovieCache;
import entities.Role;
import entities.User;
import facades.ApiFacadeImplementation;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import utils.EMF_Creator;

/**
 *
 * @author Camilla
 */
public class ApiResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    private static ImdbDTO imdb;
    private static MetaCriticDTO meta;
    private static MovieAllDTO all;
    private static MovieCountDTO count;
    private static MovieSimpleDTO simple;
    private static RottenTomatoesDTO rotten;
    private static MovieCache cache;
    private static ApiFacadeImplementation facade;

    public ApiResourceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        httpServer = startServer();
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
        facade = ApiFacadeImplementation.getApiFacade(emf);

        cache = new MovieCache("Alien", "{\"cast\":\"Tom Skerritt,Sigourney Weaver,Veronica Cartwright,Harry Dean Stanton\",\"directors\":\"Ridley Scott\",\"genres\":\"Horror,Sci-Fi\",\"imdb\":{\"rating\":8.5,\"source\":\"imdb\",\"votes\":502112},\"metaCritic\":{\"metacritic\":83,\"source\":\"metacritic\"},\"plot\":\"The commercial vessel Nostromo receives a distress call from an unexplored planet. After searching for survivors, the crew heads home only to realize that a deadly bioform has joined them.\",\"poster\":\"https://m.media-amazon.com/images/M/MV5BMmQ2MmU3NzktZjAxOC00ZDZhLTk4YzEtMDMyMzcxY2IwMDAyXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg\",\"title\":\"Alien\",\"tomatoes\":{\"criticMeter\":97,\"criticNumReviews\":100,\"criticRating\":9.0,\"source\":\"tomatoes\",\"viewerMeter\":94,\"viewerNumReviews\":453306,\"viewerRating\":3.9},\"year\":1979}", 1, new Date());
        simple = new MovieSimpleDTO("Alien", "The commercial vessel Nostromo receives a distress call from an unexplored planet. After searching for survivors, the crew heads home only to realize that a deadly bioform has joined them.", "Ridley Scott", "Horror,Sci-Fi", "Tom Skerritt,Sigourney Weaver,Veronica Cartwright,Harry Dean Stanton", 1979, "https://m.media-amazon.com/images/M/MV5BMmQ2MmU3NzktZjAxOC00ZDZhLTk4YzEtMDMyMzcxY2IwMDAyXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg");
        rotten = new RottenTomatoesDTO("tomatoes", 3.9, 9, 453306, 94, 100, 97);
        meta = new MetaCriticDTO("metacritic", 83);
        imdb = new ImdbDTO("imdb", 8.5, 502112);
        all = new MovieAllDTO("Alien", "The commercial vessel Nostromo receives a distress call from an unexplored planet. After searching for survivors, the crew heads home only to realize that a deadly bioform has joined them.", "Ridley Scott", "Horror,Sci-Fi", "Tom Skerritt,Sigourney Weaver,Veronica Cartwright,Harry Dean Stanton", 1979, "https://m.media-amazon.com/images/M/MV5BMmQ2MmU3NzktZjAxOC00ZDZhLTk4YzEtMDMyMzcxY2IwMDAyXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg", imdb, rotten, meta);
        count = new MovieCountDTO("Alien", 1);
    }

    @AfterAll
    public static void closeTestServer() {
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("delete from User").executeUpdate();
        em.createQuery("delete from Role").executeUpdate();
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        User user = new User("user", "test");
        user.addRole(userRole);
        User admin = new User("admin", "test");
        admin.addRole(adminRole);
        User both = new User("user_admin", "test");
        both.addRole(userRole);
        both.addRole(adminRole);
        User nobody = new User("nobody", "test");
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.persist(nobody);
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

    private static String securityToken;

    private static void login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                .when().post("/login")
                .then()
                .extract().path("token");
        System.out.println("TOKEN ---> " + securityToken);
    }

    private void logOut() {
        securityToken = null;
    }

    
    @Test
    public void testHelloWorld() {
        given()
                .contentType("application/json")
                .when()
                .get("/info").then()
                .statusCode(200)
                .body("msg", equalTo("Hello World"));
    }
    
    @Test
    public void testSimpleMovieData() {
        MovieSimpleDTO expResult = simple;
        login("user", "test");
        MovieSimpleDTO result
                = with()
                        .contentType("application/json")
                        .accept(ContentType.JSON)
                        .header("x-access-token", securityToken)
                        .when().request("GET", "info/movieInfoSimple/Alien").then()
                        .assertThat().log().body()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(MovieSimpleDTO.class);
        MatcherAssert.assertThat((result), equalTo(expResult));
    }

    @Test
    public void testAllMovieData() {
        MovieAllDTO expResult = all;
        login("admin", "test");
        MovieAllDTO result
                = with()
                        .contentType("application/json")
                        .accept(ContentType.JSON)
                        .header("x-access-token", securityToken)
                        .when().request("GET", "info/movieInfoAll/Alien").then()
                        .assertThat().log().body()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(MovieAllDTO.class);
        MatcherAssert.assertThat((result), equalTo(expResult));
    }

    /**
     * Test of adminMovieCount method, of class ApiResource.
     */
    @Test
    public void testAdminMovieCount() throws Exception {
        MovieCountDTO expResult = count;
        login("admin", "test");
        MovieCountDTO result
                = with()
                        .contentType("application/json")
                        .accept(ContentType.JSON)
                        .header("x-access-token", securityToken)
                        .when().request("GET", "info/movieCount/Alien").then()
                        .assertThat().log().body()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(MovieCountDTO.class);
        MatcherAssert.assertThat((result), equalTo(expResult));
    }


}
