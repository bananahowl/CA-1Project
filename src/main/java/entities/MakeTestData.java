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
        try {
            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
          //  em.persist(member5);
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
