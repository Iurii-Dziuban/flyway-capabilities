package fly;

/**
 * Created by dziubani on 4/8/2016.
 */

import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogFactory;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests shows how to run flyway migration once per whole test class via putting @FlywayTest on class
 * Run and see the logs.
 * DB file: db/flywayFeatureTestDb.mv.db
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
