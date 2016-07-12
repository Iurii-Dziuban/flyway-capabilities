package main;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by dziubani on 4/11/2016.
 */

/**
 * Simple example that shows how control to control priority of the config and programmatic properties
 * Target version in the config is 50 in java 20. When configure via properties files, already configured values will be replaced
 * In the end target version is 50 like in the config, locations are "some/folder" it is not replaced by properties, because there was no such configuration
 */
public class ConfigureViaPropertiesExample {

    private static Log log = LogFactory.getLog(ConfigureViaPropertiesExample.class);

    public static void main( String[] args )
    {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:file:./db/main");
        ds.setUsername("sa");
        ds.setPassword("");

        Map<String, String> placeholders = new HashMap<String, String>();
        placeholders.put("app", "My programmatic name");

        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setPlaceholders(placeholders);
        flyway.setTargetAsString("20");
        flyway.setLocations("some/folder");

        try {
            FileInputStream input = new FileInputStream("src/main/resources/sample.properties");
            Properties properties = new Properties();
            properties.load(input);
            flyway.configure(properties);
        } catch (FileNotFoundException e) {
            log.error("File not found exception");
        } catch (IOException e) {
            log.error("Exception reading file");
        }
        log.info("flyway target is " + flyway.getTarget());
        log.debug("locations are " + flyway.getLocations()[0]);
    }
}
