package rocks.kreig.transfers;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.spi.CDI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.helidon.microprofile.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.kreig.transfers.resource.Account;
import rocks.kreig.transfers.resource.Status;
import rocks.kreig.transfers.resource.Transfer;

import java.math.BigDecimal;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EndpointsIntegrationTest {
    private static Server server;
    private Client client;

    @BeforeAll
    public static void startTheServer() throws Exception {
        server = Main.startServer();
    }

    @BeforeEach
    public void init() {
        client = ClientBuilder.newClient();
    }

    @Test
    void testAccountEndpoint() {

        final Response response = client
                .target(getConnectionString("/v1/transfers/account/1"))
                .request()
                .get();

        assertEquals(response.getStatus(), OK.getStatusCode());
        assertNotNull(response.getEntity());

        final Account account = response.readEntity(Account.class);

        assertEquals((long) account.getId(), 1L);
    }

    @Test
    void testAccountEndpoint_notFound() {

        final Response response = client
                .target(getConnectionString("/v1/transfers/account/5"))
                .request()
                .get();

        assertEquals(response.getStatus(), NOT_FOUND.getStatusCode());

    }

    @Test
    void testTransferEndpoint() {
        final String senderAccountNumber = "11111111";
        final String receiverAccountNumber = "22222222";

        final Account originAccount = new Account(null, null, senderAccountNumber , null);
        final Account destinationAccount = new Account(null, null, receiverAccountNumber, null);

        final BigDecimal originalSenderAmount = BigDecimal.valueOf(50.00);
        final BigDecimal originalReceiverAmount = BigDecimal.valueOf(100.00);
        final BigDecimal amount = BigDecimal.valueOf(50.00);

        final Transfer transfer = new Transfer(null, originAccount, destinationAccount, amount, null);

        final Response response = client
                .target(getConnectionString("/v1/transfers/transfer"))
                .request()
                .post(Entity.entity(transfer, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(response.getStatus(), OK.getStatusCode());
        assertNotNull(response.getEntity());

        final Transfer createdTransfer = response.readEntity(Transfer.class);

        assertNotNull(createdTransfer.getStatus());
        assertEquals(createdTransfer.getStatus().getStatus(), Status.COMPLETED);

        assertEquals(createdTransfer.getOrigin().getNumber(), senderAccountNumber);
        assertEquals(createdTransfer.getDestination().getNumber(), receiverAccountNumber);

        assertEquals(createdTransfer.getOrigin().getBalance(), originalSenderAmount.subtract(amount));
        assertEquals(createdTransfer.getDestination().getBalance(), originalReceiverAmount.add(amount));

    }

    @Test
    public void testObservability() {
        Response r = client
                .target(getConnectionString("/metrics"))
                .request()
                .get();
        Assertions.assertEquals(200, r.getStatus(), "GET metrics status code");

        r = client
                .target(getConnectionString("/health"))
                .request()
                .get();
        Assertions.assertEquals(200, r.getStatus(), "GET health status code");
    }

    @AfterAll
    static void destroyClass() {
        CDI<Object> current = CDI.current();
        ((SeContainer) current).close();
    }

    private String getConnectionString(String path) {
        return "http://localhost:" + server.port() + path;
    }
}
