package fly.callback;

import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.callback.FlywayCallback;
import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogFactory;

import java.sql.Connection;

/**
 * Undo filtered from coverage
 * Created by dziubani on 4/8/2016.
 */
public abstract class AbstractFlywayCallback implements FlywayCallback {

    private static final Log LOG = LogFactory.getLog(AbstractFlywayCallback.class);

    @Override
    public void beforeUndo(Connection connection) {
        LOG.info("beforeUndo");
    }

    @Override
    public void beforeEachUndo(Connection connection, MigrationInfo migrationInfo) {
        LOG.info("beforeEachUndo");
    }

    @Override
    public void afterEachUndo(Connection connection, MigrationInfo migrationInfo) {
        LOG.info("afterEachUndo");
    }

    @Override
    public void afterUndo(Connection connection) {
        LOG.info("afterUndo");
    }
}
