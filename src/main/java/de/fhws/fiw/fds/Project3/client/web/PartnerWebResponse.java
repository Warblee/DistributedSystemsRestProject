package de.fhws.fiw.fds.Project3.client.web;

import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.Project3.client.models.PartnerClientModel;


import okhttp3.Headers;

import java.util.Collection;

public class PartnerWebResponse  extends WebApiResponse<PartnerClientModel> {

    public PartnerWebResponse(final Collection<PartnerClientModel> responseData, final Headers headers, final int lastStatusCode) {
        super(responseData, headers, lastStatusCode);
    }
}
