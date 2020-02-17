package rocks.kreig.transfers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.kreig.transfers.resource.Status;
import rocks.kreig.transfers.resource.TransferStatus;
import rocks.kreig.transfers.service.TransferService;

import javax.ws.rs.core.Response;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class TransferResourceTest {

    private TransferService transferService = mock(TransferService.class);

    private TransferResource transferResource;

    @BeforeEach
    public void init() {
        transferResource = new TransferResource(transferService);
    }

    @Test
    void testTransferStatus() {

        final long id = 0;
        final Optional<TransferStatus> transferStatus = Optional.of(new TransferStatus(Status.INITIATED, "SOME REASON"));

        given(transferService.status(id)).willReturn(transferStatus);

        final Response response = transferResource.transferStatus(id);

        then(transferService).should().status(id);

        assertAll("response",
                () -> assertNotNull(response.getEntity()),
                () -> assertEquals(response.getStatus(), Response.Status.OK.getStatusCode()));


    }

    @Test
    void testTransferStatusNotFound() {
        final long id = 0;
        final Optional<TransferStatus> transferStatus = Optional.empty();

        given(transferService.status(id)).willReturn(transferStatus);

        final Response response = transferResource.transferStatus(id);

        then(transferService).should().status(id);

        assertAll("response",
                () -> assertNull(response.getEntity()),
                () -> assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode()));

    }

    @Test
    void testTransfer() {
    }
}