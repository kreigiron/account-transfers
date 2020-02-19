package rocks.kreig.transfers;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.spi.CDI;
import javax.json.JsonObject;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import rocks.kreig.transfers.resource.Account;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MainTest {
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

        assertEquals(account.getId(), 1);
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
