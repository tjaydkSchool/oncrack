/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Junit;

import entity.Address;
import exceptions.AddressNotFoundException;
import facade.AddressFacade;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Asnorrason
 */
public class AddressJUnitTest {

    AddressFacade test;

    public AddressJUnitTest() {
        test = new AddressFacade();
    }

    @Test
    public void createAddress() throws AddressNotFoundException {
        Address a = new Address();
        a.setId(10000L);
        a.setStreet("hellostreet");
        a.setAdditionalInfo("not null ");
        assertNotNull(test.createAddress(a));
        test.deleteAddress(a);
    }

    @Test
    public void getAddressesByStreetName() throws AddressNotFoundException {
        Address a1 = new Address();
        a1.setId(30000L);
        a1.setStreet("Street");
        a1.setAdditionalInfo("here lives a1");
        test.createAddress(a1);
        Address a2 = new Address();
        a2.setId(40000L);
        a2.setStreet("Street");
        a2.setAdditionalInfo("here lives a2");
        test.createAddress(a2);
        Address a3 = new Address();
        a3.setId(50000L);
        a3.setStreet("Street");
        a3.setAdditionalInfo("here lives a3");
        test.createAddress(a3);
        List<Address> al = test.getAddressesByStreet("Street");
        assertTrue(al.size() == 3);
        test.deleteAddress(a1);
        test.deleteAddress(a2);
        test.deleteAddress(a3);
    }

    @Test
    public void getAllAddresses() throws AddressNotFoundException {
        Address a1 = new Address();
        a1.setId(30000L);
        a1.setStreet("Street");
        a1.setAdditionalInfo("here lives a1");
        test.createAddress(a1);
        Address a2 = new Address();
        a2.setId(40000L);
        a2.setStreet("Street");
        a2.setAdditionalInfo("here lives a2");
        test.createAddress(a2);
        Address a3 = new Address();
        a3.setId(50000L);
        a3.setStreet("Street");
        a3.setAdditionalInfo("here lives a3");
        test.createAddress(a3);
        List<Address> al = test.getAllAddresses();
        System.out.println("size Check" + al.size());
        assertTrue(al.size() == 4);
        test.deleteAddress(a1);
        test.deleteAddress(a2);
        test.deleteAddress(a3);
    }

    @Test
    public void updateAddress() throws AddressNotFoundException {
        Address a1 = new Address();
        a1.setId(30000L);
        a1.setStreet("Street");
        a1.setAdditionalInfo("here lives a1");
        test.createAddress(a1);
        a1.setId(30000L);
        a1.setStreet("Street 2");
        a1.setAdditionalInfo("here lives a new a1");
        test.updateAddress(a1);
        assertNotNull(test.getAddressesByStreet("Street 2"));
        test.deleteAddress(a1);
    }
    
    @Test
    public void deleteAddress() throws AddressNotFoundException{
        Address a1 = new Address();
        a1.setId(30000L);
        a1.setStreet("Street");
        test.createAddress(a1);
        test.deleteAddress(a1);
        System.out.println(test.getAddressById(30000L));
        assertNull(test.getAddressById(30000L));
    }

}
