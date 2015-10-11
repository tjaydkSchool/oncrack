package exceptions;

import com.google.gson.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CityInfoNotFoundExceptionMapper implements ExceptionMapper<CityInfoNotFoundException> {

    @Context
    ServletContext context;

    @Override
    public Response toResponse(CityInfoNotFoundException e) {
        JsonObject jo = new JsonObject();
        if (Boolean.valueOf(context.getInitParameter("cityinfo"))) {
            jo.addProperty("Code:", 404);
        }
        jo.addProperty("Message", e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(jo.toString()).build();
    }

}
