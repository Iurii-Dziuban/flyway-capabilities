package fly;

import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Test shows integration with spring test extensions via jdbc to be able to execute specific sql
 * inside a transaction (Transaction manager should be in class path and transaction mode = Isolated) or without transaction,
 * before/after test method with specific error handling policy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/simple_application_context.xml" })
@TestExecutionListeners({FlywayTestExecutionListener.class, SqlScriptsTestExecutionListener.class })
public class FlywayMigrationWithSpringSqlInvocationTest {
    private Log log = LogFactory.getLog(FlywayMigrationWithSpringSqlInvocationTest.class);

    @Test
    @FlywayTest
    @SqlGroup(@Sql(scripts = "/testSqlFiles/test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
         , config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED, errorMode = SqlConfig.ErrorMode.DEFAULT)))
    public void test() {
        log.info("Test");
    }
}
