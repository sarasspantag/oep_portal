package com.oep.services.admin.connection;

import org.springframework.jdbc.core.JdbcTemplate;

public interface IDao {
	void setJdbcTemplate(JdbcTemplate jdbcTemplate);
}
