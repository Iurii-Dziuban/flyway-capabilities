package fly;

import model.PersonTableShower;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.info.MigrationInfoDumper;
import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.dbunit.DBUnitSupport;
import org.flywaydb.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dziubani on 4/8/2016.
 */

/**
 * Test shows how to integrate DBUnit support for Flyway tests
 * FlywayDBUnitTestExecutionListener.class listener is needed for spring and configuration is done via @DBUnitSupport
 * DB file: db/flywayFeatureTestDb.mv.db
 *
 * Shows Records of Person table in the end of each test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/simple_application_context.xml" })
@TestExecutionListeners({FlywayTestExecutionListener.class, FlywayDBUnitTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class })
@FlywayTest
public class FlywayMigrationWithDbUnitTest {

    private static Log LOGGER = LogFactory.getLog(FlywayMigrationWithDbUnitTest.class);

    @Autowired
    private Flyway flyway;

    @Autowired
    private DataSource dataSourceRef;

    @Test
    @FlywayTest
    @DBUnitSupport(loadFilesForRun = { "INSERT", "dbunit3/dataset.xml"} )
    public void test() {
        LOGGER.info("Test");

        LOGGER.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));

        assertThat(PersonTableShower.printAndReturnCount(dataSourceRef)).isEqualTo(7);
    }
}
