package de.fhws.fiw.fds.Project3.server.api.states.partners_modules;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.Project3.server.api.models.Module;
import de.fhws.fiw.fds.Project3.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class DeleteSingleModule extends AbstractDeleteRelationState<Response, Module> {

    public DeleteSingleModule(ServiceContext serviceContext, long modelIdToDelete, long primaryId) {
        super(serviceContext, modelIdToDelete, primaryId);
        this.suttonResponse = new JerseyResponse<>();
    }


    @Override
    protected SingleModelResult<Module> loadModel() {
        return DaoFactory.getInstance().getPartnerModuleDao().readById(this.primaryId, this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        //deletes both the relation and the Module from the database
        //This holds the fact that modules cannot exist w/o the partner
        //and that each module is unique to its partner university
        DaoFactory.getInstance().getPartnerModuleDao().deleteRelation(this.primaryId, this.modelIdToDelete);
        return DaoFactory.getInstance( ).getModuleDao( ).delete( this.modelIdToDelete );
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerModuleUri.REL_PATH,
                PartnerModuleRelTypes.GET_ALL_MODULES,
                getAcceptRequestHeader(),
                this.primaryId);
    }
}
