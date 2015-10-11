package facade;

import entity.Address;
import exceptions.AddressNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AddressFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    EntityManager em = emf.createEntityManager();

    /**
     * Create an address in database.
     *
     * @param address creating the address object to database
     * @return Address object
     */
    public Address createAddress(Address address) {
        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();
        return address;
    }

    /**
     * Getting all addresses with given id from database.
     *
     * @param id getting addresses by id
     * @return List of address objects with given street
     * @throws exceptions.AddressNotFoundException
     */
    public Address getAddressById(long id) throws AddressNotFoundException {
        if (id != 0) {
            return (Address) em.find(Address.class, id);
        } else {
            throw new AddressNotFoundException("Address with given id not found, search for another");
        }
    }

    /**
     * Getting all addresses in database.
     *
     * @return List of address objects
     * @throws exceptions.AddressNotFoundException
     */
    public List<Address> getAllAddresses() throws AddressNotFoundException {
        TypedQuery<Address> q = em.createQuery("SELECT a FROM Address a", Address.class);
        List<Address> al = q.getResultList();
        if (al.isEmpty()) {
            throw new AddressNotFoundException("No addresses found, come back later");
        } else {
            return q.getResultList();
        }
    }

    /**
     * Getting all addresses with given street in database.
     *
     * @param street getting addresses by street name
     * @return List of address objects with given street
     * @throws exceptions.AddressNotFoundException
     */
    public List<Address> getAddressesByStreet(String street) throws AddressNotFoundException{
        TypedQuery<Address> q = em.createQuery("SELECT c FROM Address c WHERE c.street = :street", Address.class);
        q.setParameter("street", street);
        List<Address> al = q.getResultList();
        if (al.isEmpty()) {
            throw new AddressNotFoundException("No addresses found on given street, search for another");
        } else {
            return q.getResultList();
        }
    }

//    public List<Address> getAddressesByEvenNumbers(){
//        Scanner scan = new Scanner();
//        TypedQuery<Address> q = em.createQuery("SELECT c FROM Address c WHERE c.additionalInfo % 2 = 0", Address.class);
//        return q.getResultList();
//    }
    /**
     * Updating an address in database.
     *
     * @param address updating the address object from database
     * @return Address object
     * @throws exceptions.AddressNotFoundException
     */
    public Address updateAddress(Address address) throws AddressNotFoundException{
        if (em.find(Address.class, address.getId()) != null) {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            return address;
        } else {
            throw new AddressNotFoundException("No addresses found on that address, cant update");
        }
    }

    /**
     * Deleting an address in database.
     *
     * @param address deleting the address object from database
     * @return Address object
     * @throws exceptions.AddressNotFoundException
     */
    public Address deleteAddress(Address address) throws AddressNotFoundException{
        if (em.find(Address.class, address.getId()) != null) {
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
            return address;
        } else {
            throw new AddressNotFoundException("No addresses found on that address, cant delete");
        }
    }

}
