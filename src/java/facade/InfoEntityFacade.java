package facade;

import entity.InfoEntity;
import entity.Person;
import exceptions.InfoEntityNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class InfoEntityFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    EntityManager em = emf.createEntityManager();
    PersonFacade pf = new PersonFacade();
    CompanyFacade cf = new CompanyFacade();

    /**
     * Create a person in database.
     *
     * @param searchWord
     * @return Person object
     * @throws exceptions.InfoEntityNotFoundException
     */
    public List<Person> getInfoEntitiesLimitToFive(String searchWord) throws InfoEntityNotFoundException {
        TypedQuery<Person> tq = em.createQuery("SELECT p FROM Person p WHERE p.firstName = :name", Person.class);
        tq.setParameter("name", searchWord);
        tq.setMaxResults(5);
        List<Person> pl = tq.getResultList();
        if (pl.isEmpty()) {
            throw new InfoEntityNotFoundException("No person found by given name, search for another");
        } else {
            return pl;
        }
    }

    /**
     * Create a person in database.
     *
     * @param searchWord
     * @return Person object
     * @throws exceptions.InfoEntityNotFoundException
     */
    public List<Person> getInfoEntitiesLimitToThousand(String searchWord) throws InfoEntityNotFoundException {
        TypedQuery<Person> tq = em.createQuery("SELECT p FROM Person p WHERE p.firstName = :name", Person.class);
        tq.setParameter("name", searchWord);
        tq.setMaxResults(1000);
        List<Person> pl = tq.getResultList();
        if (pl.isEmpty()) {
            throw new InfoEntityNotFoundException("No person found by given name, search for another");
        } else {
            return pl;
        }
    }

    /**
     * Create a person in database.
     *
     * @param id
     * @return Person object
     * @throws exceptions.InfoEntityNotFoundException
     */
    public InfoEntity getInfoEntityById(Long id) throws InfoEntityNotFoundException {
        TypedQuery<InfoEntity> tq = em.createQuery("SELECT i FROM InfoEntity i WHERE i.id = :id", InfoEntity.class);
        tq.setParameter("id", id);
        InfoEntity ie = tq.getSingleResult();
        if (ie != null) {
            return ie;
        } else {
            throw new InfoEntityNotFoundException("No person/company found by id, search another");
        }
    }
}
