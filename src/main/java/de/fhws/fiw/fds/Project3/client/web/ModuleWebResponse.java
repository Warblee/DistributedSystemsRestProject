package de.fhws.fiw.fds.Project3.client.web;

import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.Project3.client.models.ModuleClientModel;

import okhttp3.Headers;

import java.util.Collection;

public class ModuleWebResponse  extends WebApiResponse<ModuleClientModel> {

    public ModuleWebResponse(final Collection<ModuleClientModel> responseData, final Headers headers, final int lastStatusCode) {
        super(responseData, headers, lastStatusCode);
    }
}
