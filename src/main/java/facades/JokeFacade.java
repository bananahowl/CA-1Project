package facades;

import entities.GroupMember;
import entities.Joke;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getJokeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long JokeCount = (long) em.createQuery("SELECT COUNT(m) FROM Joke m").getSingleResult();
            return JokeCount;
        } finally {
            em.close();
        }
    }

    public List<Joke> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("Select m from Joke m", Joke.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Joke getJokeByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Joke meme = em.find(Joke.class, id);
            return meme;
        } finally {
            em.close();
        }
    }

    public List<Joke> getJokeByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Joke> query
                    = em.createQuery("Select m from Joke m where m.name =:name", Joke.class);
            return query.setParameter("name", name).getResultList();
        } finally {
            em.close();
        }
    }

    public Joke addMember(String name, String color, String description) {
        Joke meme = new Joke();
        meme = new Joke(name, color,description);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(meme);
            em.getTransaction().commit();
            return meme;
        } finally {
            em.close();
        }
    }
}
