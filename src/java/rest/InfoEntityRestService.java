/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.InfoEntityNotFoundException;
import facade.InfoEntityFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Dennis
 */
@Path("infoentity")
public class InfoEntityRestService {
    
    Gson gson;
    InfoEntityFacade ief;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InfoEntityRestService
     */
    public InfoEntityRestService() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        ief = new InfoEntityFacade();
    }

    
    @GET
    @Path("search/{searchword}")
    @Produces("application/json")
    public String getInfoEntityLimitToFive(@PathParam("searchword") String searchword) throws InfoEntityNotFoundException {
        return gson.toJson(ief.getInfoEntitiesLimitToFive(searchword));
    }
    
    @GET
    @Path("searchlist/{searchword}")
    @Produces("application/json")
    public String getInfoEntityLimitToThousand(@PathParam("searchword") String searchword) throws InfoEntityNotFoundException {
        return gson.toJson(ief.getInfoEntitiesLimitToThousand(searchword));
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getInfoEntityById(@PathParam("id") Long id) throws InfoEntityNotFoundException {
        return gson.toJson(ief.getInfoEntityById(id));
    }

    
}
