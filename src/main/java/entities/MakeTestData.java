/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.GroupMember;
import facades.MemberFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emils
 */
public class MakeTestData {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        GroupMember member1 = new GroupMember("Fred", "Gul");
        GroupMember member2 = new GroupMember("Emil", "Gul");
        GroupMember member3 = new GroupMember("Simone", "Gul");
        GroupMember member4 = new GroupMember("Ahmed", "Gul");
        GroupMember member5 = new GroupMember("Arne", "Gul");
        Joke j1 = new Joke("Frøen", "Grøn", "Ribbit sagde frøen haha");
        Joke j2 = new Joke("Giraf", "Gul", "Langhals");
        Joke j3 = new Joke("Isterning", "rød", "Hvorfor har en isterning hverken arme eller ben? Den er vanskabt.");
        Joke j4 = new Joke("Shoes", "rød", "What does a pair of really fast shoes say? SHWOOOoooooees");
        Joke j5 = new Joke("David Bowie", "rød", "Hvad var David Bowie sidste hit? nok heroine");
        Joke j6 = new Joke("Jøde", "sort", "Hvad kalder man en billig omskæring? afrivning");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
            //  em.persist(member5);
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.persist(j4);
            em.persist(j5);
            em.persist(j6);
            em.getTransaction().commit();
            //Verify that books are managed and has been given a database id
            System.out.println(member1.getId());
            System.out.println(member2.getId());
            System.out.println(member3.getId());
            System.out.println(member4.getId());

        } finally {
            em.close();
        }
    }
}
