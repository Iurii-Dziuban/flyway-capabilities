package fly;

import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogFactory;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Test shows how to run flyway migration once per each test method via putting @FlywayTest on each test method.
 * Run and see the logs.
 * DB file: db/flywayFeatureTestDb.mv.db
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/simple_application_context.xml" })
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        FlywayTestExecutionListener.class,
        SqlScriptsTestExecutionListener.class,
        FlywayDBUnitTestExecutionListener.class })
public class FlywayMigrationForEachMethodTest {

    private static Log LOGGER = LogFactory.getLog(FlywayMigrationForEachMethodTest.class);

    @Test
    @FlywayTest
    public void test(){
        LOGGER.info("Test");
    }

    @Test
    @FlywayTest
    public void test2(){
        LOGGER.info("Test2");
    }
}
