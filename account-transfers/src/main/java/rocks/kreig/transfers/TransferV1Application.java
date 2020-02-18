package rocks.kreig.transfers;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.helidon.common.CollectionsHelper;

/**
 * Transfers application
 */
@ApplicationScoped
@ApplicationPath("/v1")
public class TransferV1Application extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return CollectionsHelper.setOf(TransferResource.class);
    }
}
