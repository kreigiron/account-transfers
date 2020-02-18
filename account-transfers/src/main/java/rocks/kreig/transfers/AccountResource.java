package rocks.kreig.transfers;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import rocks.kreig.transfers.resource.Account;
import rocks.kreig.transfers.service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account")
@RequestScoped
public class AccountResource {
    private final AccountService accountService;

    @Inject
    public AccountResource(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get an account by id")
    @APIResponses({
            @APIResponse(
                    description = "An account",
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Account.class))
            )

    })
    public Response account(@PathParam("id") final long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
