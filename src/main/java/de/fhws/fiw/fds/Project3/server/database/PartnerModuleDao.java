package de.fhws.fiw.fds.Project3.server.database;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.Project3.server.api.models.Module;
import de.fhws.fiw.fds.Project3.server.api.models.Partner;

public interface PartnerModuleDao extends IDatabaseRelationAccessObject<Module> {

    void initializeDatabase();

    void resetDatabase();

}