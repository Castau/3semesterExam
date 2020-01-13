
package facades;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entities.Role;
import entities.User;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author Camilla
 */
public class ApiFacadeImplementation {
    private ExecutorService executor = Executors.newCachedThreadPool();
    private String url = "https://swapi.co/api/";
    private String[] ENDPOINTS = {"people/", "planets/", "starships/", "vehicles/", "species/"};

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
    
    

    public Map<String, String> allApiData() throws InterruptedException, ExecutionException, TimeoutException {
        Map<String, String> result = new HashMap();
        Queue<Future<Pair<String, String>>> queue = new ArrayBlockingQueue(ENDPOINTS.length);

        for (String endpoint : ENDPOINTS) {
            Future<Pair<String, String>> future = executor.submit(new Callable<Pair<String, String>>() {
                @Override
                public Pair<String, String> call() throws Exception {
                    return new ImmutablePair(endpoint.substring(0, endpoint.length()-1), getApiData(url + endpoint));
                }
            });
            queue.add(future);
        }

        while (!queue.isEmpty()) {
            Future<Pair<String, String>> epPair = queue.poll();
            if (epPair.isDone()) {
                result.put(epPair.get().getKey(), epPair.get().getValue());
            } else {
                queue.add(epPair);
            }
        }
        executor.shutdown();
        return result;
    }

    private String getApiData(String url) {
        String result = "";
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json;charset=UTF-8");
            connection.setRequestProperty("user-agent", "Application");
            try (Scanner scan = new Scanner(connection.getInputStream())) {
                String response = "";
                while(scan.hasNext()) {
                    response += scan.nextLine();
                }
                Gson gson = new Gson();
                result = gson.fromJson(response, JsonObject.class).toString();
            }
        } catch (Exception e) {
            result = "";
        }
        return result;
    }
    
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
