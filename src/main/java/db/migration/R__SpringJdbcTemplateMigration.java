package db.migration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by dziubani on 4/8/2016.
 */
/**
 * Java repeatable spring jdbc template migration example. Used in the examples
 */
public class R__SpringJdbcTemplateMigration implements SpringJdbcMigration{

    private static final Log LOG = LogFactory.getLog(R__SpringJdbcTemplateMigration.class);

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        LOG.info("jdbc Template Fetch size = " + jdbcTemplate.getFetchSize());
    }
}
