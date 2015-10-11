/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Junit;

import entity.Company;
import entity.Phone;
import exceptions.CompanyNotFoundException;
import exceptions.PhoneNotFoundException;
import facade.CompanyFacade;
import facade.PhoneFacade;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Asnorrason
 */
public class CompanyJUnitTest {

    CompanyFacade test;
    PhoneFacade phonetest;

    public CompanyJUnitTest() {
        test = new CompanyFacade();
        phonetest = new PhoneFacade();
    }

    @Test
    public void createCompany() throws CompanyNotFoundException {
        Company c = new Company();
        c.setId(100000L);
        assertNotNull(test.createCompany(c));
        test.deleteCompany(c);
    }

    @Test
    public void getCompanyByCVR() throws CompanyNotFoundException {
        Company c = new Company();
        c.setId(200000L);
        c.setCvr("10303030");
        test.createCompany(c);
        assertNotNull(test.getCompanyByCVR("10303030"));
        test.deleteCompany(c);

    }

    @Test
    public void getCompanyByPhone() throws CompanyNotFoundException, PhoneNotFoundException {
        Company c = new Company();
        c.setId(300000L);
        test.createCompany(c);

        Phone phone = new Phone();
        phone.setNumber("88888888");
        phone.setIE(c);
        assertNotNull(phonetest.createPhone(phone));

        assertEquals(test.getCompanyByPhone("88888888"), c);
        phonetest.deletePhone(phone);
        
        test.deleteCompany(c);
    }

    @Test
    public void getCompanyById() throws CompanyNotFoundException {
        Company c = new Company();
        c.setId(400000L);
        c.setCvr("30303030");
        test.createCompany(c);
        assertNotNull(test.getCompanyById(400000L));
        test.deleteCompany(c);
    }

    @Test
    public void getCompanyWithMoreThanXEmployees() throws CompanyNotFoundException {
        Company comp1 = new Company();
        comp1.setId(500000L);
        comp1.setNumberEmployees(3000000);
        Company comp2 = new Company();
        comp2.setId(600000L);
        comp2.setNumberEmployees(1000000);
        Company comp3 = new Company();
        comp3.setId(700000L);
        comp3.setNumberEmployees(2800000);
        test.createCompany(comp1);
        test.createCompany(comp2);
        test.createCompany(comp3);
        assertNotNull(test.getCompaniesWithMoreThanXEmployees(2500000));
        test.deleteCompany(comp1);
        test.deleteCompany(comp2);
        test.deleteCompany(comp3);
    }

    @Test
    public void updateCompany() throws CompanyNotFoundException {
        Company c = new Company();
        c.setId(800000L);
        c.setName("Apple inc.");
        test.createCompany(c);
        assertEquals(test.getCompanyById(c.getId()).getName(), "Apple inc.");
        
        Company updC = test.getCompanyById(c.getId());
        updC.setName("Google inc.");
        test.updateCompany(updC);
        assertEquals(test.getCompanyById(c.getId()).getName(), "Google inc.");
        
        test.deleteCompany(c);
    }

    @Test
    public void deleteCompany() throws CompanyNotFoundException {
        Company c = new Company();
        c.setId(900000L);
        test.createCompany(c);
        assertNotNull(test.getCompanyById(900000L));
        test.deleteCompany(c);
    }

}
