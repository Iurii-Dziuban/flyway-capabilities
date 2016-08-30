package fly;

import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by dziubani on 4/8/2016.
 */

/**
 * Test shows how Flyway migrations can be executed before test run and work is done based on annotations.
 * If you miss to tell to use DependencyInjection or FlywayTestExecution - it won't work. This configuration is needed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/simple_application_context.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
@FlywayTest
public class FlywayImportanceOfListenersTest {

    private Log log = LogFactory.getLog(TemplateTest.class);

    @Autowired
    protected ApplicationContext context;

    protected Connection con;

    @Before
    public void setup() throws Exception {
        DataSource ds = context.getBean("dataSourceRef", DataSource.class);
        con = ds.getConnection();
    }

    @Test
    public void test(){
        log.info("Test");
    }

    @After
    public void tearDown() throws Exception {
        if (con != null) {
            if (!con.isClosed()) {
                con.rollback();
                con.close();
            }
        }
        con = null;
    }
}
