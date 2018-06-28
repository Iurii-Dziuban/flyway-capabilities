package fly;

import model.PersonTableShower;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogFactory;
import org.flywaydb.core.internal.info.MigrationInfoDumper;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test shows integration with spring test extensions via jdbc to be able to execute specific sql
 * inside a transaction (Transaction manager should be in class path and transaction mode = Isolated) or without transaction,
 * before/after test method with specific error handling policy
 *
 * Shows Records of Person table in the end of each test
 *
 * DB file: db/flywayFeatureTestDb.mv.db
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/simple_application_context.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class,
        SqlScriptsTestExecutionListener.class })
public class FlywayMigrationWithSpringSqlInvocationTest {

    private static Log LOGGER = LogFactory.getLog(FlywayMigrationWithSpringSqlInvocationTest.class);

    @Autowired
    private Flyway flyway;

    @Autowired
    private DataSource dataSourceRef;

    @Test
    @FlywayTest
    @SqlGroup(@Sql(scripts = "/testSqlFiles/test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
         , config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED, errorMode = SqlConfig.ErrorMode.DEFAULT)))
    public void testBefore() {
        LOGGER.info("Test");

        LOGGER.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));

        assertThat(PersonTableShower.printAndReturnCount(dataSourceRef)).isEqualTo(4);
    }

    @Test
    @FlywayTest
    @SqlGroup(@Sql(scripts = "/testSqlFiles/test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
            , config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED, errorMode = SqlConfig.ErrorMode.DEFAULT)))
    public void testAfter() {
        LOGGER.info("Test");

        LOGGER.info("\n" + MigrationInfoDumper.dumpToAsciiTable(flyway.info().all()));

        assertThat(PersonTableShower.printAndReturnCount(dataSourceRef)).isEqualTo(3);
    }
}
