/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Hobby;
import entity.Person;
import entity.Phone;
import exceptions.PersonNotFoundException;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class PersonFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    EntityManager em = emf.createEntityManager();

    /**
     * Create a person in database.
     *
     * @param person the person object to persist in database
     * @return Person object
     * @throws exceptions.PersonNotFoundException
     */
    public Person createPerson(Person person){
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
    }

    /**
     * Returns the person with a given id.
     *
     * @param id the id number of the person searched for
     * @return Person object
     * @throws exceptions.PersonNotFoundException
     */
    public Person getPersonById(Long id) throws PersonNotFoundException {
        if (id != 0L) {
            return em.find(Person.class, id);
        } else {
            throw new PersonNotFoundException("Person with given id not found, search for another");
        }
    }

    /**
     * Returns the person with a given phone number.
     *
     * @param number the phone number of the person searched for
     * @return Person object
     * @throws exceptions.PersonNotFoundException
     */
    public Person getPersonByPhone(String number) throws PersonNotFoundException {
        Phone phone = em.find(Phone.class, number);
        if (phone != null) {
            return em.find(Person.class, phone.getIE().getId());
        } else {
            throw new PersonNotFoundException("Person with given number not found, search for another number");
        }
    }

    /**
     * Returns a list of persons with a given hobby.
     *
     * @param hobby the hobby of which you want list of persons interested in
     * @return Person list
     * @throws exceptions.PersonNotFoundException
     */
    public List<Person> getPersonsWithHobby(String hobby) throws PersonNotFoundException {
        Collection<Person> pl = em.find(Hobby.class, hobby).getPersons();
        if (pl.isEmpty()) {
            throw new PersonNotFoundException("Person with given hobby not found, search for another hobby");
        } else {
            return (List<Person>) pl;
        }
    }

    /**
     * Returns the number of persons with a given hobby.
     *
     * @param hobby the hobby of which you want count of persons interested in
     * @return int
     * @throws exceptions.PersonNotFoundException
     */
    public int getNumberOfPersonsWithHobby(String hobby) throws PersonNotFoundException {
        int i = em.find(Hobby.class, hobby).getPersons().size();
        if (i != 0) {
            return i;
        } else {
            throw new PersonNotFoundException("No person found with given hobby, search for another hobby");
        }
    }

    /**
     * Returns a list of person living i given city.
     *
     * @param zipcode
     * @param city the zipcode of the city you want to find persons from
     * @return Person list
     */
    public List<Person> getPersonsLivingInCity(String zipcode) throws PersonNotFoundException {
        TypedQuery<Person> q = em.createQuery("SELECT i FROM InfoEntity i WHERE i.address.cityInfo.zipCode = :zipcode", Person.class);
        q.setParameter("zipcode", zipcode);
        List<Person> pl = q.getResultList();
        if (pl.isEmpty()) {
            throw new PersonNotFoundException("No persons found with given zipcode, search for another zipcode");
        } else {
            return q.getResultList();
        }
    }

    /**
     * Returns list of all persons i database
     *
     * @return List 'of persons'
     * @throws exceptions.PersonNotFoundException
     */
    public List<Person> getListOfAllPersons() throws PersonNotFoundException {
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> pl = q.getResultList();
        if (pl.isEmpty()) {
            throw new PersonNotFoundException("No persons found, come back later");
        } else {
            return q.getResultList();
        }
    }

    /**
     * Returns the first five results of persons matching the firstname
     * searchword
     *
     * @param name the firstname searchword
     * @return List 'of persons limited to 5'
     * @throws exceptions.PersonNotFoundException
     */
    public List<Person> getListOfByNameLimitFive(String name) throws PersonNotFoundException {
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p WHERE p.firstName LIKE :name", Person.class);
        q.setParameter("name", name + "%");
        q.setMaxResults(5);
        List<Person> pl = q.getResultList();
        if (pl.isEmpty()) {
            throw new PersonNotFoundException("No persons found, come back later");
        } else {
            return q.getResultList();
        }
    }

    public List<Person> getListOfByName(String name) throws PersonNotFoundException {
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p WHERE p.firstName LIKE :name", Person.class);
        q.setParameter("name", name + "%");
        List<Person> pl = q.getResultList();
        if (pl.isEmpty()) {
            throw new PersonNotFoundException("No persons found with given name, search for another");
        } else {
            return q.getResultList();
        }
    }

    public Person getPersonByName(String firstname, String lastname) throws PersonNotFoundException {
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p WHERE p.firstName LIKE :firstname AND p.lastName LIKE :lastname", Person.class);
        q.setParameter("firstname", firstname);
        q.setParameter("lastname", lastname);
        Person p = q.getSingleResult();
        if (p == null) {
            throw new PersonNotFoundException("No persons found with given firstname and lastname, search for another");
        } else {
            return q.getSingleResult();
        }
    }

    /**
     * Update a person in database.
     *
     * @param person the person object to update in database
     * @return Person object
     * @throws exceptions.PersonNotFoundException
     */
    public Person updatePerson(Person person) throws PersonNotFoundException {
        if (em.find(Person.class, person.getId()) != null) {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return person;
        } else {
            throw new PersonNotFoundException("No person found, cant update person");
        }
    }

    /**
     * Delete a person in database.
     *
     * @param person the person to be deleted from database
     * @return Person object
     * @throws exceptions.PersonNotFoundException
     */
    public Person deletePerson(Person person) throws PersonNotFoundException {
        if (em.find(Person.class, person.getId()) != null) {
            TypedQuery<Phone> tq = em.createQuery("SELECT p FROM Phone p WHERE p.IE = :ie", Phone.class);
            tq.setParameter("ie", person);
            List<Phone> phoneList = tq.getResultList();
            if (phoneList != null) {
                for (int i = 0; i < phoneList.size(); i++) {
                    Phone removeAssociationPhone = phoneList.get(i);
                    removeAssociationPhone.setIE(null);
                    em.getTransaction().begin();
                    em.persist(removeAssociationPhone);
                    em.getTransaction().commit();
                }
            }
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return person;
        } else {
            throw new PersonNotFoundException("No person found, cant delete");
        }
    }

}
