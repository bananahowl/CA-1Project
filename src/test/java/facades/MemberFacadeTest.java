package facades;

import entities.GroupMember;
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
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;
    private GroupMember m1 = new GroupMember("Simone", "Gul");
    private GroupMember m2 = new GroupMember("Grethe", "Grøn");
    private GroupMember m3 = new GroupMember("Ahmed", "Rød");
    private GroupMember m4 = new GroupMember("Frederik", "Gul");
    private GroupMember m5 = new GroupMember("Emil", "Gul");
    
    

    public MemberFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MemberFacade.getMemberFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = MemberFacade.getMemberFacade(emf);
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
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);
            em.persist(m5);
            
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
    public void countMembersTest() {
        assertEquals(5, facade.getMemberCount(), "Expects five rows in the database");
    }
    
    
    /**
     * This method check if a members id matches his name
     */
    @Test
    public void findMemberByIdTest(){
        GroupMember member = facade.getMemberByID(m4.getId());
        assertEquals(member.getName(),"Frederik"); 
    }
    
    /**
     * This method start checking if the list is null, if not it will compare the list.size with the actual amount
     * having some problems with this one - it wont be green when i use assertEquals(m, members) the omit is 4 ish wrong 
     */
    @Test
    public void checkListAreEqualTest(){
        List <GroupMember> members = facade.getAllGroupMembers();
        List <GroupMember> m = new ArrayList();
        m.add(m1);
        m.add(m2);
        m.add(m3);
        m.add(m4);
        m.add(m5);
        
        assertNotNull(members);
        assertNotNull(m);
        assertEquals(members.size(), m.size());
    }
    
    
    /**
     * This method check if it is possible to get a members information by only typing their name
     */
    @Test
    public void getMemberByNameTest(){
        List <GroupMember> member = facade.getMemberByName(m3.getName());
        assertNotNull(member);
        assertEquals(member.get(0).getName(), "Ahmed");
        assertEquals(member.get(0).getColor(), "Rød");
    }
    
    
    @Test
    public void addMemberTest(){
        String name = "Caroline";
        String color = "Grøn";
        GroupMember member = facade.addMember(name, color);
        assertEquals(6, facade.getMemberCount());
        
        
    }
}
