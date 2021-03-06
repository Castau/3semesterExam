package facades;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import dto.ImdbDTO;
import dto.MetaCriticDTO;
import dto.MovieAllDTO;
import dto.MovieCountDTO;
import dto.MovieSimpleDTO;
import dto.RottenTomatoesDTO;
import entities.MovieCache;
import entities.Role;
import entities.User;
import errorhandling.NotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author Camilla
 */
public class ApiFacadeImplementation implements ApiFacadeInterface {

    private ExecutorService executor;
    private final String url = "http://exam-1187.mydemos.dk/";
    private final Gson GSON = new Gson();

    private static ApiFacadeImplementation facade;
    private static EntityManagerFactory emf;

    private ApiFacadeImplementation() {
    }

    public static ApiFacadeImplementation getApiFacade(EntityManagerFactory _emf) {
        if (facade == null) {
            emf = _emf;
            facade = new ApiFacadeImplementation();
        }
        return facade;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public MovieSimpleDTO simpleMovieData(String movieTitle) {
        MovieSimpleDTO movieDTO = new MovieSimpleDTO();
        String[] ENDPOINTS = {"movieInfo/", "moviePoster/"};
        try {
            Map<String, String> apiData = allApiData(ENDPOINTS, movieTitle);
            JsonObject movieInfoJsonObject = new JsonParser().parse(apiData.get("movieInfo")).getAsJsonObject();
            movieDTO.setTitle(movieInfoJsonObject.get("title").getAsString());
            movieDTO.setPlot(movieInfoJsonObject.get("plot").getAsString());
            movieDTO.setDirectors(movieInfoJsonObject.get("directors").getAsString());
            movieDTO.setGenres(movieInfoJsonObject.get("genres").getAsString());
            movieDTO.setCast(movieInfoJsonObject.get("cast").getAsString());
            movieDTO.setYear(movieInfoJsonObject.get("year").getAsInt());

            JsonObject moviePosterJsonObject = new JsonParser().parse(apiData.get("moviePoster")).getAsJsonObject();
            movieDTO.setPoster(moviePosterJsonObject.get("poster").getAsString());

        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            throw new IllegalArgumentException("Json mapping in simpleMovieData went wrong");
        }
        return movieDTO;
    }

    private MovieCache isDataInCache(String title) {
        EntityManager em = getEntityManager();
        try {
            List<MovieCache> moviecacheList = em.createNamedQuery("MovieCache.getByTitle", MovieCache.class).setParameter("title", title).getResultList();
            if (!moviecacheList.isEmpty() && moviecacheList.get(0) != null) {
                upDateCount(em, moviecacheList.get(0));
                return moviecacheList.get(0);
            } else {
                return null;
            }
        } catch (IllegalStateException | PersistenceException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Database error");
        } finally {
            em.close();
        }
    }

    private void upDateCount(EntityManager em, MovieCache moviecache) {
        moviecache.setCount(moviecache.getCount() + 1);
        em.getTransaction().begin();
        em.merge(moviecache);
        em.getTransaction().commit();
    }

    private void saveToCache(MovieAllDTO movieDTO) {
        MovieCache moviecache = new MovieCache(movieDTO.getTitle(), GSON.toJson(movieDTO), 1, new Date());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(moviecache);
            em.getTransaction().commit();

        } catch (IllegalStateException | PersistenceException | IllegalArgumentException e) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Rollback of transaction saveToCache");
        } finally {
            em.close();
        }
    }

    @Override
    public MovieCountDTO getMovieCount(String title) throws NotFoundException{
        EntityManager em = getEntityManager();
        MovieCountDTO moviecountDTO = new MovieCountDTO();
        try {
            List<MovieCache> moviecacheList = em.createNamedQuery("MovieCache.getByTitle", MovieCache.class).setParameter("title", title).getResultList();
            if (!moviecacheList.isEmpty() && moviecacheList.get(0) != null) {
                moviecountDTO.setCount(moviecacheList.get(0).getCount());
                moviecountDTO.setTitle((moviecacheList.get(0).getTitle()));
                return moviecountDTO;
            } else {
                throw new NotFoundException("Movie not found");
            }
        } catch (IllegalStateException | PersistenceException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Database error");
        } finally {
            em.close();
        }
    }
    
    @Override
    public MovieAllDTO allMovieData(String movieTitle) {
        MovieAllDTO movieDTO = new MovieAllDTO();
        MetaCriticDTO metaDTO = new MetaCriticDTO();
        ImdbDTO imdbDTO = new ImdbDTO();
        RottenTomatoesDTO tomatoesDTO = new RottenTomatoesDTO();

        MovieCache moviecache = isDataInCache(movieTitle);
        if (moviecache != null) {
            System.out.println("AM IN CACHE IF");
            System.out.println("GET RESPONSE");
            System.out.println(moviecache.getResponse());
            return GSON.fromJson(moviecache.getResponse(), MovieAllDTO.class);
        }

        String[] ENDPOINTS = {"movieInfo/", "moviePoster/", "imdbScore/", "tomatoesScore/", "metacriticScore/"};
        try {
            Map<String, String> apiData = allApiData(ENDPOINTS, movieTitle);
            JsonObject movieInfoJsonObject = new JsonParser().parse(apiData.get("movieInfo")).getAsJsonObject();

            movieDTO.setTitle(movieInfoJsonObject.get("title").getAsString());
            movieDTO.setPlot(movieInfoJsonObject.get("plot").getAsString());
            movieDTO.setDirectors(movieInfoJsonObject.get("directors").getAsString());
            movieDTO.setGenres(movieInfoJsonObject.get("genres").getAsString());
            movieDTO.setCast(movieInfoJsonObject.get("cast").getAsString());
            movieDTO.setYear(movieInfoJsonObject.get("year").getAsInt());

            JsonObject moviePosterJsonObject = new JsonParser().parse(apiData.get("moviePoster")).getAsJsonObject();
            movieDTO.setPoster(moviePosterJsonObject.get("poster").getAsString());

            JsonObject imdbScoreJsonObject = new JsonParser().parse(apiData.get("imdbScore")).getAsJsonObject();
            imdbDTO.setSource(imdbScoreJsonObject.get("source").getAsString());
            imdbDTO.setRating(imdbScoreJsonObject.get("imdbRating").getAsDouble());
            imdbDTO.setVotes(imdbScoreJsonObject.get("imdbVotes").getAsInt());

            JsonObject metacriticScoreJsonObject = new JsonParser().parse(apiData.get("metacriticScore")).getAsJsonObject();
            metaDTO.setSource(metacriticScoreJsonObject.get("source").getAsString());
            metaDTO.setMetacritic(metacriticScoreJsonObject.get("metacritic").getAsInt());

            JsonObject tomatoesScoreJsonObject = new JsonParser().parse(apiData.get("tomatoesScore")).getAsJsonObject();
            tomatoesDTO.setCriticMeter(tomatoesScoreJsonObject.get("critic").getAsJsonObject().get("meter").getAsInt());
            tomatoesDTO.setCriticNumReviews(tomatoesScoreJsonObject.get("critic").getAsJsonObject().get("numReviews").getAsInt());
            tomatoesDTO.setCriticRating(tomatoesScoreJsonObject.get("critic").getAsJsonObject().get("rating").getAsDouble());
            tomatoesDTO.setSource(tomatoesScoreJsonObject.get("source").getAsString());
            tomatoesDTO.setViewerMeter(tomatoesScoreJsonObject.get("viewer").getAsJsonObject().get("meter").getAsInt());
            tomatoesDTO.setViewerNumReviews(tomatoesScoreJsonObject.get("viewer").getAsJsonObject().get("numReviews").getAsInt());
            tomatoesDTO.setViewerRating(tomatoesScoreJsonObject.get("viewer").getAsJsonObject().get("rating").getAsDouble());

            movieDTO.setImdb(imdbDTO);
            System.out.println("IMDB");
            System.out.println(imdbDTO);
            movieDTO.setMetaCritic(metaDTO);
            movieDTO.setTomatoes(tomatoesDTO);

        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            throw new IllegalArgumentException("Json mapping in allMovieData went wrong");
        }
        saveToCache(movieDTO);
        System.out.println("MADE IT TO RETURN");
        return movieDTO;
    }

    private Map<String, String> allApiData(String[] ENDPOINTS, String movieTitle) throws InterruptedException, ExecutionException, TimeoutException {
        executor = Executors.newCachedThreadPool();
        Map<String, String> movieMap = new HashMap();
        Queue<Future<Pair<String, String>>> queue = new ArrayBlockingQueue(ENDPOINTS.length);

        for (String endpoint : ENDPOINTS) {
            Future<Pair<String, String>> future = executor.submit(new Callable<Pair<String, String>>() {
                @Override
                public Pair<String, String> call() throws Exception {
                    return new ImmutablePair(endpoint.substring(0, endpoint.length() - 1), getApiData(url + endpoint + movieTitle.replace(" ", "%20")));
                }
            });
            queue.add(future);
        }

        while (!queue.isEmpty()) {
            Future<Pair<String, String>> epPair = queue.poll();
            if (epPair.isDone()) {
                movieMap.put(epPair.get().getKey(), epPair.get().getValue());
            } else {
                queue.add(epPair);
            }
        }
        executor.shutdown();
        return movieMap;
    }

    private String getApiData(String url) throws NotFoundException {
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json;charset=UTF-8");
            connection.setRequestProperty("user-agent", "Application");
            try (Scanner scan = new Scanner(connection.getInputStream(), "UTF-8")) {
                String response = "";
                while (scan.hasNext()) {
                    response += scan.nextLine();
                }
                return GSON.fromJson(response, JsonObject.class).toString();
            }
        } catch (JsonSyntaxException | IOException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean testData() {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();
            em.getTransaction().commit();

            em.getTransaction().begin();

            Role userRole = new Role("user");
            Role adminRole = new Role("admin");

            User admin = new User("admin", "admin");
            User user1 = new User("user", "user");
            User user2 = new User("karen77", "mortil3");
            User user3 = new User("vlad", "mrpresident");
            User user4 = new User("therealhat", "tophat");

            admin.addRole(adminRole);
            user1.addRole(userRole);
            user2.addRole(userRole);
            user3.addRole(userRole);
            user4.addRole(userRole);

            em.persist(userRole);
            em.persist(adminRole);

            em.persist(admin);
            em.persist(user1);
            em.persist(user2);
            em.persist(user3);
            em.persist(user4);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }
}
