package de.fhws.fiw.fds.Project3.server.api.services;

import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingPage;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import de.fhws.fiw.fds.Project3.server.api.models.Module;
import de.fhws.fiw.fds.Project3.server.api.models.Partner;
import de.fhws.fiw.fds.Project3.server.api.queries.QueryByPartnerName;
import de.fhws.fiw.fds.Project3.server.api.queries.QueryByPage;
import de.fhws.fiw.fds.Project3.server.api.states.partners_modules.*;
import de.fhws.fiw.fds.Project3.server.api.states.partners.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("partners")
public class PartnerJerseyService extends AbstractJerseyService {

    public PartnerJerseyService() {
        super();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllPartners(
            @DefaultValue("") @QueryParam("partnerName") final String partnerName,
            @DefaultValue("0") @QueryParam(PagingBehaviorUsingPage.QUERY_PARAM_PAGE) int pageNum,
            @DefaultValue("") @QueryParam("order") String order) {
        try {
            return new GetAllPartners(
                    this.serviceContext,
                    new QueryByPartnerName<>(partnerName, pageNum, order)
            ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(e.getExceptionMessage(), e.getStatus().getCode());
        }
    }

    @GET
    @Path("{id: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSinglePartner(@PathParam("id") final long id) {
        try {
            return new GetSinglePartner(this.serviceContext, id).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response
                    .status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build()
            );
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createSinglePartner(final Partner partnerModel) {
        try {
            return new PostNewPartner(this.serviceContext, partnerModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSinglePartner(@PathParam("id") final long id, final Partner partnerModel) {
        try {
            return new PutSinglePartner(this.serviceContext, id, partnerModel).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteSinglePartner(@PathParam("id") final long id) {
        try {
            return new DeleteSinglePartner(this.serviceContext, id).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{partnerId: \\d+}/modules")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllModules(@PathParam("partnerId") final long partnerId,
        @DefaultValue("0") @QueryParam("page") int pageNum) {
        try {
            return new GetAllModules(this.serviceContext, partnerId , new QueryByPage<>(partnerId, pageNum)).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{partnerId: \\d+}/modules/{moduleId: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSingleModule(@PathParam("partnerId") final long partnerId,
                                            @PathParam("moduleId") final long moduleId) {
        try {
            return new GetSingleModule( this.serviceContext, partnerId, moduleId ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @POST
    @Path("{partnerId: \\d+}/modules")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createNewModule(@PathParam("partnerId") final long partnerId, final Module module) {
        try {
            return new PostNewModule( this.serviceContext, partnerId, module ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{partnerId: \\d+}/modules/{moduleId: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateNewModule(@PathParam("partnerId") final long partnerId,
                                              @PathParam("moduleId") final long moduleId, final Module module) {
        try {
            return new PutSingleModule( this.serviceContext, partnerId, moduleId, module ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{partnerId: \\d+}/modules/{moduleId: \\d+}")
    public Response deleteModule(@PathParam("partnerId") final long partnerId,
                                           @PathParam("moduleId") final long moduleId) {
        try {
            return new DeleteSingleModule( this.serviceContext, moduleId, partnerId ).execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

}
