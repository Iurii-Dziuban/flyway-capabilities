package main;

import fly.callback.DefaultFlywayCallback;
import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogFactory;
import org.flywaydb.core.internal.exception.FlywayProUpgradeRequiredException;
import org.flywaydb.core.internal.info.MigrationInfoDumper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dziubani on 4/11/2016.
 */

/**
 * This flyway programmatic example shows abilities of undo feature.
 * Undo tries to undo specific versions
 *
 * But undo is not supported by open source version
 */
public class UndoTest {

    private static Log LOGGER = LogFactory.getLog(UndoTest.class);

    @Test(expected = FlywayProUpgradeRequiredException.class)
    public void test() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:file:./db/undoDB");
        ds.setUsername("sa");
        ds.setPassword("");

        Map<String, String> placeholders = new HashMap<String, String>();
        placeholders.put("name", "UndoFlyway");

        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setPlaceholders(placeholders);
        flyway.setCallbacks(new DefaultFlywayCallback());
        flyway.clean();

        LOGGER.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
        flyway.migrate();

        LOGGER.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));

        flyway.undo();

        LOGGER.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
    }
}
