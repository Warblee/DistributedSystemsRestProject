package de.fhws.fiw.fds.Project3.server.api.states.partners;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.Project3.server.api.queries.QueryByPartnerName;
import de.fhws.fiw.fds.Project3.server.api.models.Partner;
import jakarta.ws.rs.core.Response;

public class GetAllPartners extends AbstractGetCollectionState<Response, Partner> {

    public GetAllPartners(ServiceContext serviceContext, AbstractQuery<Response, Partner> query) {
        super(serviceContext, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUri.REL_PATH, PartnerRelTypes.CREATE_PARTNER, getAcceptRequestHeader());
        addLink(PartnerUri.REL_PATH + "?partnerName={PARTNERNAME}",PartnerRelTypes.FILTER_BY_NAME,getAcceptRequestHeader() );
        addLink(PartnerUri.REL_PATH + "?order={ORDER}", PartnerRelTypes.ORDERING, getAcceptRequestHeader());
        addLink(PartnerUri.REL_PATH + "?partnerName={PARTNERNAME}&order={ORDER}", PartnerRelTypes.FILTERANDORDER, getAcceptRequestHeader());
    }
}