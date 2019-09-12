/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.GroupMember;
import facades.MemberFacade;
import facades.MemberFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emils
 */
public class MakeTestDataJoke {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
               EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
    Joke j1 = new Joke("Frøen", "Grøn","Ribbit sagde frøen haha");
    Joke j2 = new Joke("Giraf", "Gul","Langhals");
    Joke j3 = new Joke("Isternng", "rød","Hvorfor har en isterning hverken arme eller ben? Den er vanskabt.");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.getTransaction().commit();
            //Verify that books are managed and has been given a database id
            System.out.println(j1.getId());
            System.out.println(j2.getId());
            System.out.println(j3.getId());

        } finally {
            em.close();
        }
    }
}
