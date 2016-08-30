package fly;

import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.dbunit.DBUnitSupport;
import org.flywaydb.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dziubani on 4/8/2016.
 */

/**
 * Test shows how to integrate DBUnit support for Flyway tests
 * FlywayDBUnitTestExecutionListener.class listener is needed and configuration is done via @DBUnitSupport
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/simple_application_context.xml" })
@TestExecutionListeners({FlywayTestExecutionListener.class, FlywayDBUnitTestExecutionListener.class })
@FlywayTest
public class FlywayMigrationWithDbUnitTest {

    private Log log = LogFactory.getLog(FlywayMigrationWithDbUnitTest.class);

    @Test
    @FlywayTest
    @DBUnitSupport(loadFilesForRun = { "INSERT", "dbunit3/dataset.xml"} )
    public void test() {
        log.info("Test");
    }
}
