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
import entity.Company;
import facade.CompanyFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.MediaType;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

/**
 *
 * @author Asnorrason
 */
public class CompanyApiTest {

    static EntityManagerFactory emfOriginal;
    static CompanyFacade facade;
    int idrandom;

    public CompanyApiTest() {
    }

    @BeforeClass
    public static void setupAll() {
        baseURI = "http://localhost:8080/CA2SEM3";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }

    @Test
    public void testCreateCompany() {

        Company c = new Company();
        c.setId(1000L);
        c.setName("What a name");
        c.setCvr("503010");

        given()
                .contentType(ContentType.JSON)
                .body(c)
                .when()
                .post("/company")
                .then()
                .statusCode(200);

        expect().statusCode(200).
                body(
                        "name", equalTo("What a name"),
                        "cvr", equalTo("503010"),
                        "numberEmployees", equalTo(0),
                        "marketValue", equalTo(0),
                        "id", equalTo(1000)).
                when().
                get("/company/1000");
        
        when()
                .delete("/company/1000")
                .then()
                .statusCode(200);
    }

}
