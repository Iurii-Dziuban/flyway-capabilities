package main;

import model.PersonTableShower;
import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogFactory;
import org.flywaydb.core.internal.info.MigrationInfoDumper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(PersonTableShower.printAndReturnCount(ds)).isEqualTo(0);
        flyway.setTargetAsString("2");
        flyway.migrate();
        log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
        assertThat(PersonTableShower.printAndReturnCount(ds)).isEqualTo(3);
    }
}
