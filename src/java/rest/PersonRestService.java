package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Person;
import exceptions.PersonNotFoundException;
import facade.CreateDatabase;
import facade.PersonFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

@Path("person")
public class PersonRestService {

    @Context
    private UriInfo context;
    Gson gson;
    PersonFacade pf;
    CreateDatabase cb;

    public PersonRestService() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        pf = new PersonFacade();
        cb = new CreateDatabase();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createPerson(String person){
        Person p = gson.fromJson(person, Person.class);
        pf.createPerson(p);
        return person;
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getPersonById(@PathParam("id") long id) throws PersonNotFoundException {
        return gson.toJson(pf.getPersonById(id));
    }

    @GET
    @Path("phone/{number}")
    @Produces("application/json")
    public String getPersonByPhone(@PathParam("number") String number) throws PersonNotFoundException {
        return gson.toJson(pf.getPersonByPhone(number));
    }

    @GET
    @Path("hobby/{hobby}")
    @Produces("application/json")
    public String getPersonsWithHobby(@PathParam("hobby") String hobby) throws PersonNotFoundException {
        return gson.toJson(pf.getPersonsWithHobby(hobby));
    }

    @GET
    @Path("hobby/count/{hobby}")
    @Produces("application/json")
    public String getNumberOfPersonsWithHobby(@PathParam("hobby") String hobby) throws PersonNotFoundException {
        return gson.toJson(pf.getNumberOfPersonsWithHobby(hobby));
    }

    @GET
    @Path("city/{zipcode}")
    @Produces("application/json")
    public String getPersonsLivingInCity(@PathParam("zipcode") String zipcode) throws PersonNotFoundException {
        return gson.toJson(pf.getPersonsLivingInCity(zipcode));
    }

    @GET
    @Path("complete")
    @Produces("application/json")
    public String getListOfAllPersons() throws PersonNotFoundException {
        return gson.toJson(pf.getListOfAllPersons());
    }

    @GET
    @Path("search/{name}")
    @Produces("application/json")
    public String getListOfPersonsByNameLimitFive(@PathParam("name") String name) throws PersonNotFoundException {
        return gson.toJson(pf.getListOfByNameLimitFive(name));
    }

    @GET
    @Path("searchlist/{name}")
    @Produces("application/json")
    public String getListOfPersonsByName(@PathParam("name") String name) throws PersonNotFoundException {
        return gson.toJson(pf.getListOfByName(name));
    }

    @GET
    @Path("fullname/{firstname}/{lastname}")
    @Produces("application/json")
    public String getPersonByFullName(@PathParam("firstname") String firstname, @PathParam("lastname") String lastname) throws PersonNotFoundException {
        return gson.toJson(pf.getPersonByName(firstname, lastname));
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public String updatePerson(@PathParam("id") long id, String person) throws PersonNotFoundException {
        Person p = pf.getPersonById(id);
        p = gson.fromJson(person, Person.class);
        pf.updatePerson(p);
        return gson.toJson(p);
    }

    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public String deletePerson(@PathParam("id") long id) throws PersonNotFoundException {
        Person p = pf.getPersonById(id);
        pf.deletePerson(p);
        return gson.toJson(p);
    }
    
    @GET
    @Path("populate")
    public void populate() {
        cb.populate();
    }
}
