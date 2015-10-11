/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Junit;

import entity.CityInfo;
import exceptions.CityInfoNotFoundException;
import facade.CityInfoFacade;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Asnorrason
 */
public class CityInfoJUnitTest {

    CityInfoFacade test;

    public CityInfoJUnitTest() {
        test = new CityInfoFacade();
    }

    @Test
    public void createCityInfo() throws CityInfoNotFoundException {
        CityInfo cf = new CityInfo();
        cf.setZipCode("1");
        cf.setCity("Dragør");
        test.createCityInfo(cf);
        assertNotNull(test.getCityByZipCode("1"));
        test.deleteCityInfo(cf);
    }

    @Test
    public void getAllCityInfoes() throws CityInfoNotFoundException {
        CityInfo cf1 = new CityInfo();
        cf1.setZipCode("3");
        cf1.setCity("Dragør");
        test.createCityInfo(cf1);
        CityInfo cf2 = new CityInfo();
        cf2.setZipCode("4");
        cf2.setCity("Tårnby");
        test.createCityInfo(cf2);
        CityInfo cf3 = new CityInfo();
        cf3.setZipCode("5");
        cf3.setCity("Ironforge");
        test.createCityInfo(cf3);
        CityInfo cf4 = new CityInfo();
        cf4.setZipCode("6");
        cf4.setCity("Silvermoon City");
        test.createCityInfo(cf4);

        List<CityInfo> cfl = test.getAllCityInfoes();
        assertTrue(cfl.size() == 5);
        test.deleteCityInfo(cf1);
        test.deleteCityInfo(cf2);
        test.deleteCityInfo(cf3);
        test.deleteCityInfo(cf4);
    }

    @Test
    public void getCityByZipCode() throws CityInfoNotFoundException {
        CityInfo cf = new CityInfo();
        cf.setZipCode("7");
        cf.setCity("Silvermoon City");
        test.createCityInfo(cf);
        assertNotNull(test.getCityByZipCode("7"));
        test.deleteCityInfo(cf);
    }

    @Test
    public void updateCityInfo() throws CityInfoNotFoundException {
        CityInfo cf = new CityInfo();
        cf.setZipCode("8");
        cf.setCity("Silvermoon City");
        test.createCityInfo(cf);
        System.out.println(cf.getZipCode());
        assertEquals(test.getCityByZipCode(cf.getZipCode()).getCity(), "Silvermoon City");

        CityInfo cfUpd = test.getCityByZipCode(cf.getZipCode());
        cfUpd.setCity("Ironforge");
        test.updateCityInfo(cfUpd);
        System.out.println(cfUpd.getZipCode() + cf.getZipCode());
        assertEquals(test.getCityByZipCode(cf.getZipCode()).getCity(), "Ironforge");
        test.deleteCityInfo(cf);
    }

    @Test
    public void deleteCityInfo() throws CityInfoNotFoundException {
        CityInfo cf = new CityInfo();
        cf.setZipCode("10");
        cf.setCity("Silvermoon City");
        test.createCityInfo(cf);
        assertNotNull(test.getCityByZipCode("10"));
        test.deleteCityInfo(cf);
    }

}
