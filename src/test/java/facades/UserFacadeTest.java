package facades;

import utils.EMF_Creator;
import entities.Role;
import entities.User;
import errorhandling.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//@Disabled
public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade facade;

    private static User user;
    private static User admin;
    private static User both;

    private static Role userRole;
    private static Role adminRole;

    private static final String userPass = "user";
    private static final String adminPass = "admin";
    private static final String bothPass = "both";

    public UserFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = UserFacade.getUserFacade(emf);

        user = new User("user", userPass);
        admin = new User("admin", adminPass);
        both = new User("both", bothPass);

        userRole = new Role("user");
        adminRole = new Role("admin");

        user.addRole(userRole);
        admin.addRole(adminRole);
        both.addRole(userRole);
        both.addRole(adminRole);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.getTransaction().commit();

        System.out.println("USER HASH CHECK: " + user.getUserPass());
        System.out.println("ADMIN HASH CHECK: " + admin.getUserPass());
        System.out.println("BOTH HASH CHECK: " + both.getUserPass());
        System.out.println("Created TEST Users");
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

    @Test
    public void testGetFacade() {
        assertNotNull(facade);
    }

    @Test
    public void testVerifyUsers() throws AuthenticationException {
        User expected = user;
        String username = expected.getUserName();
        User actual = facade.getVeryfiedUser(username, userPass);
        assertEquals(expected, actual);
        expected = admin;
        username = expected.getUserName();
        actual = facade.getVeryfiedUser(username, adminPass);
        assertEquals(expected, actual);
        expected = both;
        username = expected.getUserName();
        actual = facade.getVeryfiedUser(username, bothPass);
        assertEquals(expected, actual);
    }

    @Test
    public void testVerifyUsersWrong() throws Exception {
        Assertions.assertThrows(AuthenticationException.class, () -> {
            facade.getVeryfiedUser("WRONG", "WRONG");
        });
    }
}
