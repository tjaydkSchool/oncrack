package exceptions;

import com.google.gson.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

    @Context
    ServletContext context;
    
    @Override
    public Response toResponse(Throwable e) {
        JsonObject jo = new JsonObject();
        if(Boolean.valueOf(context.getInitParameter("all"))){
            jo.addProperty("Code:", 500);
        }
        jo.addProperty("Message", "Internal server Error, we are very sorry for the inconvenience");
        return Response.status(Response.Status.NOT_FOUND).entity(jo.toString()).build();
    }
    
}
