package facades;

import entities.GroupMember;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getMemberCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long memberCount = (long) em.createQuery("SELECT COUNT(m) FROM GroupMember m").getSingleResult();
            return memberCount;
        } finally {
            em.close();
        }
    }

    public List<GroupMember> getAllGroupMembers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("Select m from GroupMember m", GroupMember.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public GroupMember getMemberByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            GroupMember member = em.find(GroupMember.class, id);
            return member;
        } finally {
            em.close();
        }
    }

    public List<GroupMember> getMemberByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<GroupMember> query
                    = em.createQuery("Select m from GroupMember m where m.name =:name", GroupMember.class);
            return query.setParameter("name", name).getResultList();
        } finally {
            em.close();
        }
    }

    public GroupMember addMember(String name, String color) {
        GroupMember member = new GroupMember();
        member = new GroupMember(name, color);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();
            return member;
        } finally {
            em.close();
        }
    }

//    public static void main(String[] args) {
//        //        
//        //        memberVals.addMember("Fred", "Yellow");
//        //        memberVals.addMember("Simone", "Yellow");
//        //        memberVals.addMember("Emil", "Yellow");
//        //        memberVals.addMember("Ahmed", "Yellow");''
//        
//    } 
        public void populate(){
            EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.persist(new GroupMember("Emil", "Gul"));
            em.persist(new GroupMember("Smone", "Gul"));
            em.persist(new GroupMember("Amhed", "Gul"));
            em.persist(new GroupMember("Fred", "Gul"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    


}
