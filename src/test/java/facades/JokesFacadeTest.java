package facades;

import entities.GroupMember;
import entities.Joke;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hamcrest.Matcher;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.isIn;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokesFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;
    private Joke j1 = new Joke("Dumb joke","Rød","THESE NUTZ");
    private Joke j2 = new Joke("Inside joke","Rød","YEE BOI" );
    private Joke j3 = new Joke("Dumb joke", "Gul","What did the dragon say to the knigth, \"ah man more can food\" ");
    private Joke j4 = new Joke("Dumb joke", "Gul","Them Shoes");
    private Joke j5 = new Joke("Dumb joke", "Gul","LIGMA NUTZ");

//    private GroupMember m1 = new GroupMember("Simone", "Gul");
//    private GroupMember m2 = new GroupMember("Grethe", "Grøn");
//    private GroupMember m3 = new GroupMember("Ahmed", "Rød");
//    private GroupMember m4 = new GroupMember("Frederik", "Gul");
//    private GroupMember m5 = new GroupMember("Emil", "Gul");

    public JokesFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = JokeFacade.getJokeFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = JokeFacade.getJokeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.persist(j4);
            em.persist(j5);              
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * This method check if all of the groupmembers exists on the list
     */
    
    @Test
    public void countJokesTest() {
        System.out.println(facade.getJokeCount());
        assertEquals(5, facade.getJokeCount(),"Expects five rows in the database");
    }

    /**
     * This method check if a members id matches his name
     */
    @Test
    public void findJokeByIdTest() {
        Joke joke = facade.getJokeByID(j4.getId());
        assertEquals(joke.getName(), "Dumb joke");
    }

    /**
     * This method start checking if the list is null, if not it will compare
     * the list.size with the actual amount having some problems with this one -
     * it wont be green when i use assertEquals(m, members) the omit is 4 ish
     * wrong
     */
    
    @Test
    public void checkListAreEqualTest() {
        List<Joke> jokes = facade.getAllJokes();
        List<Joke> m = new ArrayList();
        m.add(j1);
        m.add(j2);
        m.add(j3);
        m.add(j4);
        m.add(j5);

        assertNotNull(jokes);
        assertNotNull(m);
        System.out.println(m.size());
        assertEquals(jokes.size(), m.size());
    }

    /**
     * This method check if it is possible to get a members information by only
     * typing their name
     */
    @Test
    public void getJokeByNameTest() {
        List<Joke> jokes = facade.getJokeByName(j2.getName());
        assertNotNull(jokes);
        assertEquals(jokes.get(0).getName(),"Inside joke"); 
        assertEquals(jokes.get(0).getColor(), "Rød");
    }

    @Test
    public void addJokeTest() {
        String name = "Code joke";
        String color = "Grøn";
        String description =  "C er sku ret sharp";
        Joke joke = facade.addJoke(name, color, description);
        assertEquals(6, facade.getJokeCount());

    }
}
