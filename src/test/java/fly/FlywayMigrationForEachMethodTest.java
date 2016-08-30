package fly;

import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
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
 * Test shows how to run flyway migration once per each test method @FlywayTest on each method
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/simple_application_context.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class, SqlScriptsTestExecutionListener.class, FlywayDBUnitTestExecutionListener.class })
public class FlywayMigrationForEachMethodTest {

    private Log log = LogFactory.getLog(FlywayMigrationForEachMethodTest.class);

    @Test
    @FlywayTest
    public void test(){
        log.info("Test");
    }

    @Test
    @FlywayTest
    public void test2(){
        log.info("Test2");
    }
}
