/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Junit;

import entity.Phone;
import exceptions.PhoneNotFoundException;
import facade.PhoneFacade;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Asnorrason
 */
public class PhoneJUnitTest {

    PhoneFacade test;

    public PhoneJUnitTest() {
        test = new PhoneFacade();
    }

    @Test
    public void createPhone() throws PhoneNotFoundException {
        Phone ph = new Phone();
        ph.setNumber("88888888");
        ph.setDescription("Leasy");
        test.createPhone(ph);
        assertNotNull(ph.getNumber());
        test.deletePhone(ph);
    }

    @Test
    public void getPhoneInfo() throws PhoneNotFoundException {
        Phone ph = new Phone();
        ph.setNumber("98888888");
        ph.setDescription("Teasy");
        test.createPhone(ph);
        assertEquals(test.getPhoneInfo(ph.getNumber()).getDescription(), "Teasy");
        test.deletePhone(ph);
    }

    @Test
    public void getAllPhones() throws PhoneNotFoundException {
        Phone ph1 = new Phone();
        ph1.setNumber("98888888");
        ph1.setDescription("Teasy");
        test.createPhone(ph1);
        Phone ph2 = new Phone();
        ph2.setNumber("88888888");
        ph2.setDescription("Leasy");
        test.createPhone(ph2);

        List<Phone> pl = test.getListOfAllPhoneNumbers();
        assertTrue(pl.size() == 2);
        test.deletePhone(ph1);
        test.deletePhone(ph2);
    }

    @Test
    public void updatePhone() throws PhoneNotFoundException {
        Phone ph = new Phone();
        ph.setNumber("98888888");
        ph.setDescription("Teasy");
        test.createPhone(ph);
        assertEquals(test.getPhoneInfo(ph.getNumber()).getDescription(), "Teasy");

        Phone phUpd = test.getPhoneInfo(ph.getNumber());
        phUpd.setDescription("Neasy");
        test.updatePhone(phUpd);
        assertEquals(test.getPhoneInfo(ph.getNumber()).getDescription(), "Neasy");
        test.deletePhone(ph);
    }

    @Test
    public void deletePhone() throws PhoneNotFoundException {
        Phone ph = new Phone();
        ph.setNumber("98888888");
        ph.setDescription("Teasy");
        test.createPhone(ph);
        assertNotNull(test.getPhoneInfo("98888888"));
        test.deletePhone(ph);
    }

}
