package main;

import fly.callback.DefaultFlywayCallback;
import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogFactory;
import org.flywaydb.core.internal.exception.FlywayProUpgradeRequiredException;
import org.flywaydb.core.internal.info.MigrationInfoDumper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dziubani on 4/11/2016.
 */

/**
 * Undo test in main with commons logging with log4j2 as default
 */
public class UndoMain {

    private static Log LOGGER = LogFactory.getLog(UndoMain.class);

    public static void main(String[] args) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:file:./db/undoMainDB");
        ds.setUsername("sa");
        ds.setPassword("");

        Map<String, String> placeholders = new HashMap<String, String>();
        placeholders.put("name", "UndoMainFlyway");

        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setPlaceholders(placeholders);
        flyway.setCallbacks(new DefaultFlywayCallback());
        flyway.clean();

        LOGGER.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
        flyway.migrate();

        LOGGER.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));

        try {
            flyway.undo();
        } catch (FlywayProUpgradeRequiredException e) {
            LOGGER.info(e.getMessage());
        }

        LOGGER.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
    }
}
