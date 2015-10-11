/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Junit;

import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import exceptions.AddressNotFoundException;
import exceptions.CityInfoNotFoundException;
import exceptions.HobbyNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.PhoneNotFoundException;
import facade.AddressFacade;
import facade.CityInfoFacade;
import facade.HobbyFacade;
import facade.PersonFacade;
import facade.PhoneFacade;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Asnorrason
 */
public class PersonJUnitTest {

    PersonFacade test;
    PhoneFacade ptest;
    HobbyFacade htest;
    CityInfoFacade citest;
    AddressFacade atest;

    public PersonJUnitTest() {
        test = new PersonFacade();
        ptest = new PhoneFacade();
        htest = new HobbyFacade();
        citest = new CityInfoFacade();
        atest = new AddressFacade();
    }

    @Test
    public void createAndDeletePerson() throws PersonNotFoundException {
        Person person = new Person();
        person.setId(6666L);
        test.createPerson(person);

        test.deletePerson(person);
    }

    @Test
    public void getPersonByPhoneNumber() throws PersonNotFoundException, PhoneNotFoundException {
        Person person = new Person();
        person.setId(6666L);
        test.createPerson(person);

        Phone phone = new Phone();
        phone.setNumber("4444");
        phone.setIE(person);
        ptest.createPhone(phone);
        assertNotNull(ptest.getPhoneInfo("4444"));

        assertEquals(test.getPersonByPhone("4444"), person);

        test.deletePerson(person);
        ptest.deletePhone(phone);
    }

    @Test
    public void getPersonById() throws PersonNotFoundException {
        Person p = new Person();
        p.setId(340000L);
        test.createPerson(p);

        assertNotNull(test.getPersonById(340000L));
        test.deletePerson(p);
    }

//    @Test
//    public void getPersonsWithHobby() {
//        Person p = new Person();
//        p.setId(450000L);
//        p.setFirstName("Polle");
//        test.createPerson(p);
//
//        Hobby h = new Hobby();
//        h.setName("Numseklask");
//        htest.createHobby(h);
//
//        h.addPersontoHobby(p);
//        p.addHobbytoPerson(h);
//        
//        System.out.println(test.getNumberOfPersonsWithHobby("Numseklask"));
//        
//        test.deletePerson(p);
//        htest.deleteHobby(h);
//
//        NOT SUPPORTED
//    }
    @Test
    public void getNumberOfPersonsWithHobby() throws PersonNotFoundException, HobbyNotFoundException {
//        Hobby h = new Hobby();
//        h.setName("Ridning");
//        htest.createHobby(h);
//
//        Person p = new Person();
//        p.setId(350000L);
//        p.setLastName("Frank");
//        test.createPerson(p);
//
//        assertNotNull(test.getNumberOfPersonsWithHobby("Ridning"));
//        test.deletePerson(p);
//        htest.deleteHobby(h);

    }

    @Test
    public void getPersonsLivingInCity() throws PersonNotFoundException, AddressNotFoundException, CityInfoNotFoundException {

        CityInfo c = new CityInfo();
        c.setZipCode("10101010");
        c.setCity("Copenhagen");
        citest.createCityInfo(c);

        Address a = new Address();
        a.setId(1200L);
        a.setStreet("BullerGade");
        a.setCityInfo(c);
        atest.createAddress(a);
        
        Person p = new Person();
        p.setId(350000L);
        p.setLastName("Frank");
        p.setAddress(a);
        test.createPerson(p);
        Person p1 = new Person();
        p1.setId(360000L);
        p1.setLastName("Frant");
        test.createPerson(p1);
        

        List<Person> pl = test.getPersonsLivingInCity("10101010");
        assertNotNull(pl.size() == 4);
        
        test.deletePerson(p1);
        test.deletePerson(p);
        
        atest.deleteAddress(a);
        
        citest.deleteCityInfo(c);
    }

    @Test
    public void updatePerson() throws PersonNotFoundException {
        Person person = new Person();
        person.setId(8888L);
        person.setFirstName("Original");
        test.createPerson(person);
        assertEquals(test.getPersonById(person.getId()).getFirstName(), "Original");

        Person updatedPerson = test.getPersonById(person.getId());
        updatedPerson.setFirstName("Updated");
        test.updatePerson(updatedPerson);
        assertEquals(test.getPersonById(person.getId()).getFirstName(), "Updated");

        test.deletePerson(person);
    }

}
