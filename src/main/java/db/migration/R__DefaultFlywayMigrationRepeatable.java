package db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;
import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;

import java.sql.Connection;

/**
 * Created by dziubani on 4/8/2016.
 */

/**
 * Java repeatable jdbc migration example. Used in the examples
 */
public class R__DefaultFlywayMigrationRepeatable implements JdbcMigration {
    private static final Log LOG = LogFactory.getLog(R__DefaultFlywayMigrationRepeatable.class);

    @Override
    public void migrate(Connection connection) throws Exception {
        LOG.info("JDBC Repeatable migration executed");
    }
}
