package de.fhws.fiw.fds.Project3.server.api.states.partners_modules;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.Project3.server.api.models.Module;
import de.fhws.fiw.fds.Project3.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class GetSingleModule extends AbstractGetRelationState<Response, Module> {

    public GetSingleModule(ServiceContext serviceContext, long primaryId, long requestedId) {
        super(serviceContext, primaryId, requestedId);
        this.suttonResponse = new JerseyResponse<>(); 
    }


    @Override protected SingleModelResult<Module> loadModel( )
    {
        return DaoFactory.getInstance( ).getPartnerModuleDao( ).readById(this.primaryId, this.requestedId );
    }

    @Override
    protected boolean clientKnowsCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( PartnerModuleUri.REL_PATH_ID, PartnerModuleRelTypes.UPDATE_SINGLE_MODULE, getAcceptRequestHeader( ),
                this.primaryId, this.requestedId );
        addLink( PartnerModuleUri.REL_PATH_ID, PartnerModuleRelTypes.DELETE_MODULE, getAcceptRequestHeader( ),
                this.primaryId, this.requestedId );
        addLink( PartnerModuleUri.REL_PATH, PartnerModuleRelTypes.GET_ALL_MODULES, getAcceptRequestHeader( ),
                this.primaryId);
    }
}
