package rest;

import dto.MovieAllDTO;
import dto.MovieCountDTO;
import dto.MovieSimpleDTO;
import facades.ApiFacadeImplementation;
import facades.ApiFacadeInterface;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 * @author 
 */
@Path("info")
public class ApiResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private ApiFacadeInterface facade = ApiFacadeImplementation.getApiFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("movieInfoSimple/{title}")
    @Produces({MediaType.APPLICATION_JSON})
    public MovieSimpleDTO simpleMovieData(@PathParam("title") String title) {
        return facade.simpleMovieData(title);
    }
    
    @GET
    @Path("movieInfoAll/{title}")
    @RolesAllowed({"admin", "user"})
    @Produces({MediaType.APPLICATION_JSON})
    public MovieAllDTO allMovieData(@PathParam("title") String title) {
        return facade.allMovieData(title);
    }

    @GET
    @Path("movieCount/{title}")
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public MovieCountDTO adminMovieCount(@PathParam("title") String title) {
        return facade.getMovieCount(title);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }


    @GET
    @Path("testdata")
    @Produces({MediaType.APPLICATION_JSON})
    public String populateDatabase() {

        boolean success = facade.testData();

        if (success) {
            return "{\"message\":\"Database populated with dummy data\"}";
        } else {
            return "{\"message\":\"Failed to populate database\"}";
        }
    }
}
