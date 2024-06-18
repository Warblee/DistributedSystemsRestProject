package de.fhws.fiw.fds.Project3.server.api.states.partners;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.Project3.server.api.models.Partner;
import de.fhws.fiw.fds.Project3.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PostNewPartner extends AbstractPostState<Response, Partner> {

    public PostNewPartner(ServiceContext serviceContext, Partner modelToStore) {
        super(serviceContext, modelToStore);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getPartnerDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {
        //empty, as the link should be stored in the Location header.
    }
}
