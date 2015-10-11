/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Company;
import exceptions.CompanyNotFoundException;
import exceptions.PhoneNotFoundException;
import facade.CompanyFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Asnorrason
 */
@Path("company")
public class CompanyRestService {

    Gson gson;
    CompanyFacade cfacade;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CompanyRest
     */
    public CompanyRestService() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        cfacade = new CompanyFacade();
    }

    @GET
    @Path("cvr/{number}")
    @Produces("application/json")
    public String getCompanyByCvr(@PathParam("number") String cvr) throws CompanyNotFoundException {
        return gson.toJson(cfacade.getCompanyByCVR(cvr));
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getCompanyById(@PathParam("id") long id) throws CompanyNotFoundException {
        return gson.toJson(cfacade.getCompanyById(id));
    }

    @GET
    @Path("phone/{number}")
    @Produces("application/json")
    public String getCompanyByPhone(@PathParam("number") String number) throws CompanyNotFoundException, PhoneNotFoundException {
        return gson.toJson(cfacade.getCompanyByPhone(number));
    }

    @GET
    @Path("employees/{number}")
    @Produces("application/json")
    public String getCompaniesByWithXEmployees(@PathParam("number") int number) throws CompanyNotFoundException {
        return gson.toJson(cfacade.getCompaniesWithMoreThanXEmployees(number));
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String createCompany(String company) {
        Company c = gson.fromJson(company, Company.class);
        cfacade.createCompany(c);
        return company;
//        cfacade.createCompany(gson.fromJson(company, Company.class));
    }

    @GET
    @Path("search/{name}")
    @Produces("application/json")
    public String getListOfByNameLimitFive(@PathParam("name") String name) throws CompanyNotFoundException {
        return gson.toJson(cfacade.getListOfByNameLimitFive(name));
    }

    @GET
    @Path("searchlist/{name}")
    @Produces("application/json")
    public String getListOfByName(@PathParam("name") String name) throws CompanyNotFoundException {
        return gson.toJson(cfacade.getListOfByName(name));
    }

    @GET
    @Path("complete")
    @Produces("application/json")
    public String getCompleteList() throws CompanyNotFoundException {
        return gson.toJson(cfacade.getCompleteList());
    }

    @PUT
    @Consumes("application/json")
    public void updateCompany(String company) throws CompanyNotFoundException {
        cfacade.updateCompany(gson.fromJson(company, Company.class));
    }

    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public void deleteCompany(@PathParam("id") String company) throws CompanyNotFoundException {
        cfacade.deleteCompany(gson.fromJson(company, Company.class));
    }

}
