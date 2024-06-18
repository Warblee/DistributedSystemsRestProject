package de.fhws.fiw.fds.Project3.server.api.states.partners_modules;

import de.fhws.fiw.fds.Project3.Start;

public interface PartnerModuleUri {
    String PATH_ELEMENT = "partners/{id}/modules";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}
