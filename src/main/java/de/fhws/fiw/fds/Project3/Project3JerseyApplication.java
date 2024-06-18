package de.fhws.fiw.fds.Project3;

import de.fhws.fiw.fds.sutton.server.api.AbstractJerseyApplication;
import de.fhws.fiw.fds.Project3.server.api.services.DispatcherJerseyService;
import de.fhws.fiw.fds.Project3.server.api.services.PartnerJerseyService;
import jakarta.ws.rs.ApplicationPath;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class Project3JerseyApplication extends AbstractJerseyApplication {

    @Override
    protected Set<Class<?>> getServiceClasses() {
        final Set<Class<?>> returnValue = new HashSet<>();

        returnValue.add(PartnerJerseyService.class);
        returnValue.add(DispatcherJerseyService.class);

        return returnValue;
    }

}
