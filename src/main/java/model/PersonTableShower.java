package model;

import org.flywaydb.core.internal.util.logging.Log;
import org.flywaydb.core.internal.util.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by iurii.dziuban on 06/07/2017.
 */
public class PersonTableShower {

    private static Log LOGGER = LogFactory.getLog(PersonTableShower.class);
    private static final String PERSON_SELECT_QUERY = "SELECT * FROM PUBLIC.PERSON";

    public static int printAndReturnCount(DataSource dataSource) {
        new PersonTableShower();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Person> query = jdbcTemplate.query(PERSON_SELECT_QUERY, new PersonMapper());
        query.stream().sorted().map(Person::toString).forEach(LOGGER::info);
        return query.size();
    }
}
