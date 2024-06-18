package de.fhws.fiw.fds.Project3.server.api.states.partners_modules;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.Project3.server.api.models.Module;
import de.fhws.fiw.fds.Project3.server.api.states.partners.PartnerRelTypes;
import de.fhws.fiw.fds.Project3.server.api.states.partners.PartnerUri;
import jakarta.ws.rs.core.Response;

public class GetAllModules extends AbstractGetCollectionRelationState<Response, Module> {

    public GetAllModules(ServiceContext serviceContext, long primaryId, AbstractRelationQuery<Response, Module> query) {
        super(serviceContext, primaryId, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerModuleUri.REL_PATH,
                PartnerModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);
        addLink(PartnerUri.REL_PATH_ID, PartnerRelTypes.GET_SINGLE_PARTNER, getAcceptRequestHeader(), this.primaryId);

    }
}