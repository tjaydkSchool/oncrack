package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Phone;
import exceptions.PhoneNotFoundException;
import facade.PhoneFacade;
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

@Path("phone")
public class PhoneRestService {

    Gson gson;
    PhoneFacade pf;

    @Context
    private UriInfo context;

    public PhoneRestService() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        pf = new PhoneFacade();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createPhone(String content) {
        Phone phone = gson.fromJson(content, Phone.class);
        pf.createPhone(phone);
        return content;
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public String getListOfAllPhoneNumbers() throws PhoneNotFoundException {
        return gson.toJson(pf.getListOfAllPhoneNumbers());
    }

    @GET
    @Path("{number}")
    @Produces("application/json")
    public String getPhoneByNumber(@PathParam("number") String phoneNumber) throws PhoneNotFoundException {
        return gson.toJson(pf.getPhoneInfo(phoneNumber));
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public String updatePhone(String content) throws PhoneNotFoundException {
        Phone phone = gson.fromJson(content, Phone.class);
        pf.updatePhone(phone);
        return content;
    }

    @DELETE
    @Path("{number}")
    @Consumes("application/json")
    @Produces("application/json")
    public void deletePhone(@PathParam("number") String content) throws PhoneNotFoundException {
        pf.deletePhone(gson.fromJson(content, Phone.class));
    }
}
