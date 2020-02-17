package rocks.kreig.transfers;

import rocks.kreig.transfers.resource.Transfer;
import rocks.kreig.transfers.resource.TransferStatus;
import rocks.kreig.transfers.service.TransferService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Optional;

@Path("/v1/transfer")
public class TransferResource {
    private TransferService transferService;

    @Inject
    public TransferResource( final TransferService transferService) {
        this.transferService = transferService;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferStatus(@PathParam("id") final long id) {
        final Optional<TransferStatus> transferStatus = transferService.status(id);

        if (transferStatus.isPresent()) {
            return Response.ok(transferStatus).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response transfer(final Transfer transfer) {

        final Optional<Transfer> createdTransfer = transferService.transfer(transfer);

        if(createdTransfer.isPresent()) {
            final long transferId = createdTransfer.get().getId();
            return Response.created(UriBuilder.fromResource(this.getClass()).path(String.valueOf(transferId)).build()).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Unable to create resource").build();
    }

}
