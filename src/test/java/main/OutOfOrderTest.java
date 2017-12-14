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
 * Example how you can invoke migration with version number less than current one. Via set out of order true
 */
public class OutOfOrderTest {

    private static Log log = LogFactory.getLog(OutOfOrderTest.class);

    @Test
    public void test() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:file:./db/outOfOrderDb");
        ds.setUsername("sa");
        ds.setPassword("");

        Map<String, String> placeholders = new HashMap<String, String>();
        placeholders.put("name", "Flyway");

        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setPlaceholders(placeholders);

        flyway.clean();
        flyway.migrate();
        log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
        assertThat(PersonTableShower.printAndReturnCount(ds)).isEqualTo(3);

        flyway.setOutOfOrder(true);
        flyway.setLocations("classpath:db/migration", "classpath:other_migration");
        log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
        flyway.migrate();
        log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
        assertThat(PersonTableShower.printAndReturnCount(ds)).isEqualTo(4);
    }
}
