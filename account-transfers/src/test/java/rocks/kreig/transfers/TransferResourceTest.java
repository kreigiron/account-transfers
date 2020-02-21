package rocks.kreig.transfers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.kreig.transfers.resource.Account;
import rocks.kreig.transfers.resource.Status;
import rocks.kreig.transfers.resource.Transfer;
import rocks.kreig.transfers.resource.TransferStatus;
import rocks.kreig.transfers.service.TransferService;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
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
    void testTransferResource() {

        final Long id = 1L;
        final Optional<Transfer> transfer = Optional.of(new Transfer(id, new Account(), new Account(), BigDecimal.ZERO, new TransferStatus()));

        given(transferService.transfer(transfer.get())).willReturn(transfer);

        final Response response = transferResource.transfer(transfer.get());

        then(transferService).should().transfer(transfer.get());

        assertAll("response",
                () -> assertNotNull(response.getEntity()),
                () -> assertEquals(Response.Status.OK.getStatusCode(), response.getStatus()),
                () -> assertEquals(id, ((Transfer) response.getEntity()).getId()));

    }

    @Test
    void testTransferStatusNotFound() {

        final Long id = 1L;
        final Transfer transfer = new Transfer();
        final Optional<Transfer> empty = Optional.empty();
        given(transferService.transfer(transfer)).willReturn(empty);

        final Response response = transferResource.transfer(transfer);

        then(transferService).should().transfer(transfer);

        assertAll("response",
                () -> assertNull(response.getEntity()),
                () -> assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus()));

    }

}