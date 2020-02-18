package rocks.kreig.transfers.flyway;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 * Startup database schema migrator
 * */
@ApplicationScoped
public class FlywayMigrator {
    private final DataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(FlywayMigrator.class);

    @Inject
    public FlywayMigrator(@Named("transferDataSource") final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void initFlyway(@Observes @Initialized(ApplicationScoped.class) Object init) {
        logger.info("Starting to migrate the database schema with Flyway");

        final Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        logger.info("Successfully applied latest schema changes");
    }
}
