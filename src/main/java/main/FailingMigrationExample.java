package main;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.info.MigrationInfoDumper;
import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dziubani on 4/11/2016.
 */

/**
 * Example of using failing migration and handling failed migration in order to fix flyway scheme table via 'repair' method
 */
public class FailingMigrationExample {

    private static Log log = LogFactory.getLog(FailingMigrationExample.class);

    public static void main( String[] args )
    {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:file:./db/main");
        ds.setUsername("sa");
        ds.setPassword("");

        Map<String, String> placeholders = new HashMap<String, String>();
        placeholders.put("app", "Fly");

        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setPlaceholders(placeholders);
        flyway.clean();
        flyway.setBaselineVersionAsString("3");
        flyway.baseline();
        flyway.setLocations("failing_migration_sql", "db/migration");
        log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
        try {
            flyway.migrate();
        } catch (RuntimeException e) {
            log.error("ERROR", e);
            log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
            flyway.repair();
            log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
        }
    }
}
