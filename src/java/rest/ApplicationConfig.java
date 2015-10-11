/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Dennis
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(exceptions.AddressNotFoundExceptionMapper.class);
        resources.add(exceptions.CityInfoNotFoundExceptionMapper.class);
        resources.add(exceptions.CompanyNotFoundExceptionMapper.class);
        resources.add(exceptions.GenericExceptionMapper.class);
        resources.add(exceptions.HobbyNotFoundExceptionMapper.class);
        resources.add(exceptions.InfoEntityNotFoundExceptionMapper.class);
        resources.add(exceptions.NotFoundExceptionMapper.class);
        resources.add(exceptions.PersonNotFoundExceptionMapper.class);
        resources.add(exceptions.PhoneNotFoundExceptionMapper.class);
        resources.add(rest.AddressRestService.class);
        resources.add(rest.CityInfoRestService.class);
        resources.add(rest.CompanyRestService.class);
        resources.add(rest.HobbyRestService.class);
        resources.add(rest.InfoEntityRestService.class);
        resources.add(rest.PersonRestService.class);
        resources.add(rest.PhoneRestService.class);
    }
    
}
