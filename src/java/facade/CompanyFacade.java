/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import entity.Phone;
import exceptions.CompanyNotFoundException;
import exceptions.PhoneNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Asnorrason
 */
public class CompanyFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    EntityManager em = emf.createEntityManager();

    /**
     * Create a company in database.
     *
     * @param company creating the company object to database
     * @return Company object
     */
    public Company createCompany(Company company) {
        em.getTransaction().begin();
        em.persist(company);
        em.getTransaction().commit();
        return company;
    }

    /**
     * Returns a company with a given cvr number.
     *
     * @param cvr the cvr of the company searched for
     * @return Company object
     * @throws exceptions.CompanyNotFoundException
     */
    public Company getCompanyByCVR(String cvr) throws CompanyNotFoundException {
        TypedQuery<Company> q = em.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class);
        q.setParameter("cvr", cvr);
        Company c = q.getSingleResult();
        if (c != null) {
            return c;
        } else {
            throw new CompanyNotFoundException("No company found by given cvr number, search for another");
        }
    }

    /**
     * Returns a company with a given phone number.
     *
     * @param number the phone number of the company searched for
     * @return Company object
     * @throws exceptions.CompanyNotFoundException
     * @throws exceptions.PhoneNotFoundException
     */
    public Company getCompanyByPhone(String number) throws CompanyNotFoundException, PhoneNotFoundException {
        Phone phone = em.find(Phone.class, number);
        if (phone != null) {
            Company c = em.find(Company.class, phone.getIE().getId());
            if (c != null) {
                return c;
            } else {
                throw new CompanyNotFoundException("No company found by given phone number, search for another");
            }
        } else {
            throw new PhoneNotFoundException("No phone found by given number, search for another");
        }
    }

    /**
     * Get a Company by given Id
     *
     * @param id the id number of the company searched for
     * @return Company object
     * @throws exceptions.CompanyNotFoundException
     */
    public Company getCompanyById(long id) throws CompanyNotFoundException {
        Company c = em.find(Company.class, id);
        if (c != null) {
            return c;
        } else {
            throw new CompanyNotFoundException("No company found with given id, search for another");
        }
    }

    /**
     * Returns a list of companies with more than a given number of employees.
     *
     * @param number the number of companies with employees greater than
     * searched for
     * @return Company list
     * @throws exceptions.CompanyNotFoundException
     */
    public List<Company> getCompaniesWithMoreThanXEmployees(int number) throws CompanyNotFoundException {
        TypedQuery<Company> q = em.createQuery("SELECT c from Company c WHERE c.numberEmployees > :number", Company.class);
        q.setParameter("number", number);
        List<Company> c = q.getResultList();
        if (c != null) {
            return c;
        } else {
            throw new CompanyNotFoundException("No company found with employees numbers higher than searched for, type a lower amount");
        }
    }

    /**
     * Returns the first five results of companies matching the name searchword
     *
     * @param name the name searchword
     * @return List 'of companies limited to 5'
     * @throws exceptions.CompanyNotFoundException
     */
    public List<Company> getListOfByNameLimitFive(String name) throws CompanyNotFoundException {
        TypedQuery<Company> q = em.createQuery("SELECT c FROM Company c WHERE c.name LIKE :name", Company.class);
        q.setParameter("name", name + "%");
        q.setMaxResults(5);
        List<Company> c = q.getResultList();
        if (c != null) {
            return c;
        } else {
            throw new CompanyNotFoundException("No companies found, come back later");
        }
    }

    /**
     * Returns a list of person with the given name
     *
     * @param name the name searchword
     * @return List 'of companies limited to 5'
     * @throws exceptions.CompanyNotFoundException
     */
    public List<Company> getListOfByName(String name) throws CompanyNotFoundException {
        TypedQuery<Company> q = em.createQuery("SELECT c FROM Company c WHERE c.name LIKE :name", Company.class);
        q.setParameter("name", name + "%");
        List<Company> c = q.getResultList();
        if (c != null) {
            return c;
        } else {
            throw new CompanyNotFoundException("No companies found by given name, search for another name");
        }
    }

    /**
     * Returns a list with all companies
     *
     * @return List 'of companies limited to 5'
     * @throws exceptions.CompanyNotFoundException
     */
    public List<Company> getCompleteList() throws CompanyNotFoundException {
        TypedQuery<Company> q = em.createQuery("SELECT c FROM Company c", Company.class);
        List<Company> c = q.getResultList();
        if (c != null) {
            return c;
        } else {
            throw new CompanyNotFoundException("No companies found, come back later");
        }
    }

    /**
     * Update a company in database.
     *
     * @param company updating the company object from database
     * @return Company object
     * @throws exceptions.CompanyNotFoundException
     */
    public Company updateCompany(Company company) throws CompanyNotFoundException{
        if (em.find(Company.class, company.getId()) != null) {
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
            return company;
        } else {
            throw new CompanyNotFoundException("No company found, couldnt update");
        }
    }

    /**
     * Delete a company in database.
     *
     * @param company deleting the company object from database
     * @return Company object
     * @throws exceptions.CompanyNotFoundException
     */
    public Company deleteCompany(Company company) throws CompanyNotFoundException{
        if (em.find(Company.class, company.getId()) != null) {
            TypedQuery<Phone> tq = em.createQuery("SELECT p FROM Phone p WHERE p.IE = :ie", Phone.class);
            tq.setParameter("ie", company);
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
            em.remove(company);
            em.getTransaction().commit();
            return company;
        } else {
            throw new CompanyNotFoundException("No company found, couldnt delete");
        }
    }

}
