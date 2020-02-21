package rocks.kreig.transfers;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.helidon.common.CollectionsHelper;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import rocks.kreig.transfers.exception.mapper.RuntimeExceptionMapper;

/**
 * Transfers application v1 resource aggregator
 */
@ApplicationScoped
@ApplicationPath("/v1/transfers")
@OpenAPIDefinition(info = @Info(
        title = "Account transfers application",
        version = "1.0.0",
        contact = @Contact(
                name = "Erik Giron")
),
        servers = {
                @Server(url = "/v1/transfers",
                        description = "localhost")
        }
)
public class TransferV1Application extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return CollectionsHelper.setOf(
                TransferResource.class,
                AccountResource.class,
                RuntimeExceptionMapper.class);
    }
}
