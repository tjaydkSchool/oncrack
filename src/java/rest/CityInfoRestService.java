/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.CityInfo;
import exceptions.CityInfoNotFoundException;
import facade.CityInfoFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
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
@Path("cityinfo")
public class CityInfoRestService {

    Gson gson;
    CityInfoFacade cifacade;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CityInfoRestService
     */
    public CityInfoRestService() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        cifacade = new CityInfoFacade();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String createCityInfo(String cityinfo) {
        CityInfo a = gson.fromJson(cityinfo, CityInfo.class);
        cifacade.createCityInfo(a);
        return cityinfo;
    }

    @GET
    @Path("all")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllCityInfoes() throws CityInfoNotFoundException {
        return gson.toJson(cifacade.getAllCityInfoes());
    }

    @GET
    @Path("{zipcode}")
    @Produces("application/json")
    @Consumes("application/json")
    public String getCityByZipCode(String zipcode) throws CityInfoNotFoundException {
        return gson.toJson(cifacade.getCityByZipCode(zipcode));
    }

    @PUT
    @Consumes("application/json")
    public void updateCityInfo(String cityinfo) throws CityInfoNotFoundException {
        cifacade.updateCityInfo(gson.fromJson(cityinfo, CityInfo.class));
    }

    @DELETE
    @Path("{zipcode}")
    @Consumes("application/json")
    public void deleteAddress(@PathParam("zipcode") String cityinfo) throws CityInfoNotFoundException {
        cifacade.deleteCityInfo(gson.fromJson(cityinfo, CityInfo.class));
    }
}