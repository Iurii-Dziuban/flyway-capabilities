package main;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.info.MigrationInfoDumper;
import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dziubani on 4/11/2016.
 */

/**
 * Example with setting Target version to be migrated to by Flyway. In default configuration it is the last available one.
 */
public class SetTargetVersionTest {
    private static Log log = LogFactory.getLog(SetTargetVersionTest.class);

    @Test
    public void test() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:file:./db/setTargetVersionDb");
        ds.setUsername("sa");
        ds.setPassword("");

        Map<String, String> placeholders = new HashMap<String, String>();
        placeholders.put("name", "[Flyway]");

        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setPlaceholders(placeholders);
        flyway.setTargetAsString("1");
        flyway.clean();
        flyway.migrate();
        log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
        flyway.setTargetAsString("2");
        flyway.migrate();
        log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
    }
}
