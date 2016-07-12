package fly;

/**
 * Created by dziubani on 4/8/2016.
 */

import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests shows hot to run flyway migration once per whole test class @FlywayTest on class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/simple_application_context.xml" })
@TestExecutionListeners({FlywayTestExecutionListener.class})
@FlywayTest
public class FlywayMigrationOnePerClassTest {

    private Log log = LogFactory.getLog(FlywayMigrationOnePerClassTest.class);

    @Test
    public void test1(){
        log.info("Test1");
    }

    @Test
    public void test2(){
        log.info("Test2");
    }

}
