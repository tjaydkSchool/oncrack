/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateDatabase {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    EntityManager em = emf.createEntityManager();
    
    
    
    public void populate() {
        CityInfo city = new CityInfo("3000", "Test");
        Address add = new Address("Street 2", "2nd right", city);
        Person person = new Person("Alju", "Hara");
        Hobby hobby = new Hobby("Paracour", "Awesome ass shit");
        person.addHobbytoPerson(hobby);
        person.setAddress(add);
        Company company = new Company("Apple", "Electronics", "5843905", 20, 10000000);
        Phone phone = new Phone("555-555-test", "Mobile", person);
        Phone phoneComp = new Phone("555-555-comp", "Company Phone", company);
        
        em.getTransaction().begin();
        em.persist(city);
        em.persist(add);
        em.persist(hobby);
        em.persist(person);
        em.persist(company);
        em.persist(phone);
        em.persist(phoneComp);
        em.getTransaction().commit();
    }
}
