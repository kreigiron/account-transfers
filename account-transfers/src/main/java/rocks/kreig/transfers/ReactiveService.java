package rocks.kreig.transfers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.helidon.microprofile.server.RoutingPath;
import io.helidon.security.SecurityContext;
import io.helidon.webserver.Routing;
import io.helidon.webserver.Service;

/**
 * TODO javadoc.
 */
@ApplicationScoped
@RoutingPath("/reactive")
public class ReactiveService implements Service {
    @Inject
    private SecurityContext securityContext;

    @Override
    public void update(Routing.Rules rules) {
        rules.get("/", (req, res) -> res.send("Context: " + securityContext));
    }
}
