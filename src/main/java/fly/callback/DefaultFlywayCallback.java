package fly.callback;

import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogFactory;

import java.sql.Connection;

/**
 * Created by dziubani on 4/8/2016.
 */

/**
 * Java callback example of flyway java callbacks. On each callback method makes logs.
 */
public class DefaultFlywayCallback extends AbstractFlywayCallback {

    private static final Log LOG = LogFactory.getLog(DefaultFlywayCallback.class);

    @Override
    public void beforeClean(Connection dataConnection) {
        LOG.info("beforeClean");
    }

    @Override
    public void afterClean(Connection dataConnection) {
        LOG.info("afterClean");
    }

    @Override
    public void beforeMigrate(Connection dataConnection) {
        LOG.info("beforeMigrate");
    }

    @Override
    public void afterMigrate(Connection dataConnection) {
        LOG.info("afterMigrate");
    }

    @Override
    public void beforeEachMigrate(Connection dataConnection, MigrationInfo info) {
        LOG.info("beforeEachMigrate");
    }

    @Override
    public void afterEachMigrate(Connection dataConnection, MigrationInfo info) {
        if (info.getVersion() != null) {
            String version = info.getVersion().toString();
            LOG.info(String.format("afterEachMigrate: Version %s: Do something", version));
        } else {
            LOG.info("afterEachMigrate");
        }
    }

    @Override
    public void beforeValidate(Connection dataConnection) {
        LOG.info("beforeValidate");
    }

    @Override
    public void afterValidate(Connection dataConnection) {
        LOG.info("afterValidate");
    }

    @Override
    public void beforeBaseline(Connection connection) {
        LOG.info("beforeBaseline");
    }

    @Override
    public void afterBaseline(Connection connection) {
        LOG.info("afterBaseline");
    }

    @Override
    public void beforeRepair(Connection dataConnection) {
        LOG.info("beforeRepair");
    }

    @Override
    public void afterRepair(Connection dataConnection) {
        LOG.info("afterRepair");
    }

    @Override
    public void beforeInfo(Connection dataConnection) {
        LOG.info("beforeInfo");
    }

    @Override
    public void afterInfo(Connection dataConnection) {
        LOG.info("afterInfo");
    }
}
