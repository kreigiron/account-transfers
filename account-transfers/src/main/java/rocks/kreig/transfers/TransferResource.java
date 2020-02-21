package rocks.kreig.transfers;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import rocks.kreig.transfers.resource.Transfer;
import rocks.kreig.transfers.resource.TransferStatus;
import rocks.kreig.transfers.service.TransferService;

import javax.enterprise.context.RequestScoped;
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

/**
 * Endpoint controller for transfer resource
 */
@Path("/transfer")
@Tag(name = "Transfer resource", description = "Create transfer and check transfer statuses")
@RequestScoped
public class TransferResource {
    private final TransferService transferService;

    @Inject
    public TransferResource(final TransferService transferService) {
        this.transferService = transferService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response transfer(final Transfer transfer) {

        final Optional<Transfer> createdTransfer = transferService.transfer(transfer);

        if (createdTransfer.isPresent()) {
            return Response.ok(createdTransfer.get()).build();
        }
        return Response.status(Response.Status.NO_CONTENT.getStatusCode(), "Unable to create resource").build();
    }

}
