package de.fhws.fiw.fds.Project3.server.api.states.partners;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.Project3.server.api.models.Partner;
import de.fhws.fiw.fds.Project3.server.api.states.partners_modules.PartnerModuleRelTypes;
import de.fhws.fiw.fds.Project3.server.api.states.partners_modules.PartnerModuleUri;
import de.fhws.fiw.fds.Project3.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class GetSinglePartner extends AbstractGetState<Response, Partner> {

    public GetSinglePartner(ServiceContext serviceContext, long requestedId) {
        super(serviceContext, requestedId);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<Partner> loadModel() {
        return DaoFactory.getInstance().getPartnerDao().readById(this.requestedId);
    }

    @Override
    protected boolean clientKnowsCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink( PartnerUri.REL_PATH_ID, PartnerRelTypes.UPDATE_SINGLE_PARTNER, getAcceptRequestHeader( ),
                this.requestedId );
        addLink( PartnerUri.REL_PATH_ID, PartnerRelTypes.DELETE_SINGLE_PARTNER, getAcceptRequestHeader( ),
                this.requestedId );
        addLink( PartnerModuleUri.REL_PATH, PartnerModuleRelTypes.CREATE_MODULE, getAcceptRequestHeader( ),
                this.requestedId );
        addLink( PartnerModuleUri.REL_PATH, PartnerModuleRelTypes.GET_ALL_MODULES, getAcceptRequestHeader( ),
                this.requestedId );
        addLink( PartnerUri.REL_PATH, PartnerRelTypes.GET_ALL_PARTNERS, getAcceptRequestHeader());
    }
}