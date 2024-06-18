package de.fhws.fiw.fds.Project3.server.database;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.Project3.server.api.models.Partner;

public interface PartnerDao extends IDatabaseAccessObject<Partner> {

    CollectionModelResult<Partner> readByName(String partnerName, SearchParameter searchParameter);

    void initializeDatabase();

    void resetDatabase();
}