package fly;

import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.dbunit.DBUnitSupport;
import org.flywaydb.test.dbunit.FlywayDBUnitTestExecutionListener;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Template to create different test scenarios with flyway
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/simple_application_context.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class, SqlScriptsTestExecutionListener.class, FlywayDBUnitTestExecutionListener.class })
@FlywayTest
public class TemplateTest
{
    private Log log = LogFactory.getLog(TemplateTest.class);
    @Autowired
    protected ApplicationContext context;

    protected Connection con;

    /**
     * Open a connection to database for test execution statements
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {

        context.toString();

        DataSource ds = (DataSource) context.getBean("dataSourceRef");

        con = ds.getConnection();
    }

    @Test
    //@FlywayTest
    public void test(){
       log.info("Test");
   }

    @Test
    //@FlywayTest
    public void test2(){
        log.info("Test");
    }

    @Test
    @FlywayTest(locationsForMigrate = "loadsql")
    @Sql(scripts = "/testSqlFiles/test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void test3() {
        log.info("Test");
    }

    @Test
    @FlywayTest
    @DBUnitSupport(loadFilesForRun = { "INSERT", "dbunit3/dataset.xml"} )
    public void test4() {

    }

    /**
     * Close the connection
     *
     * @throws Exception
     */
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
