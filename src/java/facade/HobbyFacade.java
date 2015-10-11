package facade;

import entity.Hobby;
import exceptions.HobbyNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class HobbyFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    EntityManager em = emf.createEntityManager();

    /**
     * Create hobby and persist in database
     *
     * @param hobby
     * @return hobby object
     */
    public Hobby createHobby(Hobby hobby) {
        em.getTransaction().begin();
        em.persist(hobby);
        em.getTransaction().commit();
        return hobby;
    }

    /**
     * Get list of all hobbies in database
     *
     * @return list "of hobbies"
     * @throws exceptions.HobbyNotFoundException
     */
    public List<Hobby> getListOfAllHobbies() throws HobbyNotFoundException {
        TypedQuery<Hobby> tq = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
        List<Hobby> hl = tq.getResultList();
        if (hl.isEmpty()) {
            throw new HobbyNotFoundException("No Hobbies found, come back later");
        } else {
            return hl;
        }
    }

    /**
     * Get hobby object by hobby name
     *
     * @param hobby
     * @return hobby object
     * @throws exceptions.HobbyNotFoundException
     */
    public Hobby getHobbyByName(String hobby) throws HobbyNotFoundException {
        Hobby h = em.find(Hobby.class, hobby);
        if (hobby != null) {
            return h;
        } else {
            throw new HobbyNotFoundException("No hobby found by given name, search for another");
        }
    }

    /**
     * Updates given hobby if exists in database
     *
     * @param hobby
     * @return hobby object
     * @throws exceptions.HobbyNotFoundException
     */
    public Hobby updateHobby(Hobby hobby) throws HobbyNotFoundException{
        if (em.find(Hobby.class, hobby.getName()) != null) {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
            return hobby;
        } else {
            throw new HobbyNotFoundException("No hobby found, couldnt update");
        }
    }

    /**
     * Delete hobby from database if exists
     *
     * @param hobby
     * @return hobby object
     * @throws exceptions.HobbyNotFoundException
     */
    public Hobby deleteHobby(Hobby hobby) throws HobbyNotFoundException {
        if (em.find(Hobby.class, hobby.getName()) != null) {
            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
            return hobby;
        } else {
            throw new HobbyNotFoundException("No hobby found, couldnt delete");
        }
    }
}
