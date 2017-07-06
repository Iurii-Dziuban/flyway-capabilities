package main;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dziubani on 4/11/2016.
 */

/**
 * Simple example that shows how control to control priority of the config and programmatic properties
 * Target version in the config is 50 in java 20. When configure via properties files,
 * already configured values will be replaced
 * In the end target version is 50 like in the config,
 * locations are "some/folder" it is not replaced by properties, because there was no such configuration
 */
public class ConfigureViaPropertiesTest {

    private static Log LOGGER = LogFactory.getLog(ConfigureViaPropertiesTest.class);

    @Test
    public void test() throws IOException {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:file:./db/configureViaPropertiesDb");
        ds.setUsername("sa");
        ds.setPassword("");

        Map<String, String> placeholders = new HashMap<String, String>();
        placeholders.put("app", "My programmatic name");

        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setPlaceholders(placeholders);
        flyway.setTargetAsString("20");
        flyway.setLocations("some/folder");

        FileInputStream input = new FileInputStream("src/main/resources/sample.properties");
        Properties properties = new Properties();
        properties.load(input);
        flyway.configure(properties);

        LOGGER.info("flyway target is " + flyway.getTarget());
        LOGGER.info("locations are " + flyway.getLocations()[0]);
        assertThat(flyway.getTarget()).isEqualTo(MigrationVersion.fromVersion("50"));
        assertThat(flyway.getLocations()).containsExactly("classpath:some/folder");
        assertThat(flyway.getPlaceholders().get("app")).isEqualTo("My programmatic name");
    }
}
