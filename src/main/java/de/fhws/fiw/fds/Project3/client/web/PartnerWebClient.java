package de.fhws.fiw.fds.Project3.client.web;

import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.Project3.client.models.PartnerClientModel;
import de.fhws.fiw.fds.Project3.client.models.ModuleClientModel;



import java.io.IOException;

public class PartnerWebClient {
    private GenericWebClient<PartnerClientModel> client;
    private GenericWebClient<ModuleClientModel> clientMod;

    public PartnerWebClient() {
        this.client = new GenericWebClient<>();
    }

    public PartnerWebResponse getDispatcher( String url ) throws IOException
    {
        return createResponse( this.client.sendGetSingleRequest( url ) );
    }


    public PartnerWebResponse getSinglePartner(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, PartnerClientModel.class));
    }
    

    public PartnerWebResponse getCollectionOfPartners(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url, PartnerClientModel.class));
    }

    public PartnerWebResponse postNewPartner(String url, PartnerClientModel partner)
            throws IOException {
        return createResponse(this.client.sendPostRequest(url, partner));
    }

    public PartnerWebResponse putPartner(String url, PartnerClientModel partner) throws IOException {
        return createResponse(this.client.sendPutRequest(url, partner));
    }

    public PartnerWebResponse deletePartner(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public PartnerWebResponse resetDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/resetdatabase"));
    }

    public PartnerWebResponse initializeDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/initializedatabase"));
    }

    private PartnerWebResponse createResponse(WebApiResponse<PartnerClientModel> response) {
        return new PartnerWebResponse(response.getResponseData(), response.getResponseHeaders(),
                response.getLastStatusCode());
    }
    


}
