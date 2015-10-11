package facade;

import entity.CityInfo;
import exceptions.CityInfoNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CityInfoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    EntityManager em = emf.createEntityManager();

    /**
     * Create cityInfo in database.
     *
     * @param cityinfo creating the cityinfo object to database
     * @return CityInfo object
     */
    public CityInfo createCityInfo(CityInfo cityinfo) {
        em.getTransaction().begin();
        em.persist(cityinfo);
        em.getTransaction().commit();
        return cityinfo;
    }

    /**
     * Return a list of all cityInfoes from database.
     *
     * @return CityInfo list
     * @throws exceptions.CityInfoNotFoundException
     */
    public List<CityInfo> getAllCityInfoes() throws CityInfoNotFoundException {
        TypedQuery<CityInfo> q = em.createQuery("SELECT c FROM CityInfo c", CityInfo.class);
        List<CityInfo> cil = q.getResultList();
        if (cil.isEmpty()) {
            throw new CityInfoNotFoundException("No cities found, come back later");
        } else {
            return q.getResultList();
        }
    }

    /**
     * Return a single CityInfo by zipcode
     *
     * @param zipcode getting a single cityinfo by given zipcode
     * @return CityInfo object
     * @throws exceptions.CityInfoNotFoundException
     */
    public CityInfo getCityByZipCode(String zipcode) throws CityInfoNotFoundException{
        CityInfo ci = em.find(CityInfo.class, zipcode);
        if(ci != null){
        return ci;
        } else {
            throw new CityInfoNotFoundException("No city found by given zipcode, search by another");
        }
    }

    /**
     * Update CityInfo from database.
     *
     * @param cityinfo updating the cityinfo object from database
     * @return CityInfo object
     * @throws exceptions.CityInfoNotFoundException
     */
    public CityInfo updateCityInfo(CityInfo cityinfo) throws CityInfoNotFoundException{
        if (em.find(CityInfo.class, cityinfo.getZipCode()) != null) {
            em.getTransaction().begin();
            em.persist(cityinfo);
            em.getTransaction().commit();
            return cityinfo;
        } else {
            throw new CityInfoNotFoundException("No city found, couldnt update");
        }
    }

    /**
     * Delete CityInfo from database.
     *
     * @param cityinfo deleting cityinfo object from database
     * @return Company object
     * @throws exceptions.CityInfoNotFoundException
     */
    public CityInfo deleteCityInfo(CityInfo cityinfo) throws CityInfoNotFoundException {
        if (em.find(CityInfo.class, cityinfo.getZipCode()) != null) {
            em.getTransaction().begin();
            em.remove(cityinfo);
            em.getTransaction().commit();
            return cityinfo;
        } else {
            throw new CityInfoNotFoundException("No city found, couldnt delete");
        }
    }

}
