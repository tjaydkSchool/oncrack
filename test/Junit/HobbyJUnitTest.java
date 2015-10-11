/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Junit;

import entity.Hobby;
import exceptions.HobbyNotFoundException;
import facade.HobbyFacade;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Asnorrason
 */
public class HobbyJUnitTest {

    HobbyFacade test;

    public HobbyJUnitTest() {
        test = new HobbyFacade();
    }

    @Test
    public void createAndGetHobbyByName() throws HobbyNotFoundException {
        Hobby h = new Hobby();
        h.setName("Ring Ridning");
        h.setDescription("Du skal ride ringen godt og grundigt");
        test.createHobby(h);
        assertNotNull(test.getHobbyByName("Ring Ridning"));
        test.deleteHobby(h);
    }

    @Test
    public void getAllHobbies() throws HobbyNotFoundException {
        Hobby h1 = new Hobby();
        h1.setName("Ring Ridning");
        test.createHobby(h1);
        Hobby h2 = new Hobby();
        h2.setName("Hoolahop");
        test.createHobby(h2);
        Hobby h3 = new Hobby();
        h3.setName("Zumba");
        test.createHobby(h3);

        List<Hobby> hl = test.getListOfAllHobbies();
        assertTrue(hl.size() == 3);
        test.deleteHobby(h1);
        test.deleteHobby(h2);
        test.deleteHobby(h3);
    }

    @Test
    public void updateHobby() throws HobbyNotFoundException {
        Hobby h1 = new Hobby();
        h1.setName("Ring Ridning");
        h1.setDescription("Rid på en hest");
        test.createHobby(h1);
        assertEquals(test.getHobbyByName(h1.getName()).getDescription(), "Rid på en hest");

        Hobby hUpd = test.getHobbyByName(h1.getName());
        hUpd.setDescription("Hvad hvis man ikke gider ride");
        test.updateHobby(hUpd);
        assertEquals(test.getHobbyByName(h1.getName()).getDescription(), "Hvad hvis man ikke gider ride");

        test.deleteHobby(h1);
    }

    @Test
    public void deleteHobby() throws HobbyNotFoundException {
        Hobby h = new Hobby();
        h.setName("Ring Ridning");
        h.setDescription("Du skal ride ringen godt og grundigt");
        test.createHobby(h);
        test.deleteHobby(h);
        assertNull(test.getHobbyByName("Ring Ridning"));
    }

}
