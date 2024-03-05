package ru.javawebinar.topjava.repository.jdbc.meal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class JdbcHsqlMealRepository extends AbstractJdbcMealRepository<Timestamp> {

    public JdbcHsqlMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    protected Timestamp toDbDateTime(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt);
    }
}
