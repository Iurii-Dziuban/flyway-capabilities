package main;

import fly.callback.DefaultFlywayCallback;
import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.info.MigrationInfoDumper;
import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dziubani on 4/11/2016.
 */

/**
 * This flyway programmatic example shows abilities of baseline feature.
 * Baseline is a specific version from which flyway will start migrations
 *
 * Running and see that migrations with version less when baseline are not executed
 */
public class BaseLineTest {

    private static Log log = LogFactory.getLog(BaseLineTest.class);

    @Test
    public void test() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:file:./db/baseLineDb");
        ds.setUsername("sa");
        ds.setPassword("");

        Map<String, String> placeholders = new HashMap<String, String>();
        placeholders.put("name", "MyFlyway");

        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setPlaceholders(placeholders);
        flyway.setCallbacks(new DefaultFlywayCallback());
        flyway.clean();
        flyway.setBaselineVersionAsString("3");
        flyway.baseline();
        log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
        flyway.migrate();
        log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
    }
}
