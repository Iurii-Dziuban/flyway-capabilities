package main;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.info.MigrationInfoDumper;
import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dziubani on 4/8/2016.
 */

/**
 * Template to create different scenarios with flyway
 */
public class TemplateApp
{
    private static Log log = LogFactory.getLog(TemplateApp.class);

    public static void main( String[] args )
    {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:file:./db/main");
        ds.setUsername("sa");
        ds.setPassword("");

        Map<String, String> placeholders = new HashMap<String, String>();
        placeholders.put("name", "Flyway");

        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setPlaceholders(placeholders);
        //flyway.setLocations("classpath:db/migration");
        //flyway.setTargetAsString("1");
        //flyway.setCallbacksAsClassNames("fly.callback.DefaultFlywayCallback");
        //flyway.setOutOfOrder(true);
        //flyway.setBaselineVersion(MigrationVersion.fromVersion("2"));
        flyway.clean();
        flyway.migrate();
        log.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));
    }
}
