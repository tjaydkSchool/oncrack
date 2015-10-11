/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restapi;

import org.junit.BeforeClass;
import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import entity.Person;
import facade.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.MediaType;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

/**
 *
 * @author Asnorrason
 */
public class PersonApiTest {

    static EntityManagerFactory emfOriginal;
    static PersonFacade facade;

    public PersonApiTest() {
    }

    @BeforeClass
    public static void setupAll() {
        baseURI = "http://localhost:8080/CA2SEM3";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }
    
    @Test
    public void testCreateGetDeletePerson(){
    
        Person p = new Person();
        p.setId(1000L);
        p.setFirstName("Lars");
        p.setLastName("Larsen");
        
        given()
                .contentType(ContentType.JSON)
                .body(p)
                .when()
                .post("/person")
                .then()
                .statusCode(200);
        
        expect().statusCode(200).
                body(
                        "firstName", equalTo("Lars"),
                        "lastName", equalTo("Larsen")).
                when().
                get("/person/1000");
        when()
                .delete("/person/1000")
                .then()
                .statusCode(200);
    }
}
