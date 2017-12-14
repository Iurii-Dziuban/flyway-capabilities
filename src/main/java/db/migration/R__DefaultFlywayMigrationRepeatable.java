package db.migration;

import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogFactory;
import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;

/**
 * Created by dziubani on 4/8/2016.
 */

/**
 * Java repeatable jdbc migration example. Repeated if checksum is changed
 */
public class R__DefaultFlywayMigrationRepeatable implements JdbcMigration {
    private static final Log LOG = LogFactory.getLog(R__DefaultFlywayMigrationRepeatable.class);

    @Override
    public void migrate(Connection connection) throws Exception {
        LOG.info("JDBC Repeatable migration executed");
    }
}
