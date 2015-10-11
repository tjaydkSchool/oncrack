package facade;

import entity.Hobby;
import entity.Phone;
import exceptions.PhoneNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PhoneFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    EntityManager em = emf.createEntityManager();

    /**
     * Create phone and persist in database
     *
     * @return phone object
     */
    public Phone createPhone(Phone phone) {
        em.getTransaction().begin();
        em.persist(phone);
        em.getTransaction().commit();
        return phone;
    }

    /**
     * Get list of all phone numbers in database
     *
     * @return list "of phones"
     * @throws exceptions.PhoneNotFoundException
     */
    public List<Phone> getListOfAllPhoneNumbers() throws PhoneNotFoundException {
        TypedQuery<Phone> tq = em.createQuery("SELECT p FROM Phone p", Phone.class);
        List<Phone> pl = tq.getResultList();
        if (pl.isEmpty()) {
            throw new PhoneNotFoundException("No phone numbers found, come back later");
        } else {
            return pl;
        }
    }

    /**
     * Get phone object by phone number
     *
     * @param number
     * @return phone object
     * @throws exceptions.PhoneNotFoundException
     */
    public Phone getPhoneInfo(String number) throws PhoneNotFoundException {
        Phone p = em.find(Phone.class, number);
        if (p != null) {
            return p;
        } else {
            throw new PhoneNotFoundException("No phone numbers found by given number, search for another");
        }
    }

    /**
     * Update phone and persist in database if exists
     *
     * @param phone
     * @return phone object
     * @throws exceptions.PhoneNotFoundException
     */
    public Phone updatePhone(Phone phone) throws PhoneNotFoundException {
        if (em.find(Phone.class, phone.getNumber()) != null) {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
            return phone;
        } else {
            throw new PhoneNotFoundException("No Phone found, couldnt update");
        }
    }

    /**
     * Delete phone from database if exists
     *
     * @param phone
     * @return phone object
     * @throws exceptions.PhoneNotFoundException
     */
    public Phone deletePhone(Phone phone) throws PhoneNotFoundException {
        if (em.find(Phone.class, phone.getNumber()) != null) {
            em.getTransaction().begin();
            em.remove(phone);
            em.getTransaction().commit();
            return phone;
        } else {
            throw new PhoneNotFoundException("No Phone found, couldnt delete");
        }
    }
}
