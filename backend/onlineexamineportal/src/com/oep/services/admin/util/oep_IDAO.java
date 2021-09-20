package com.oep.services.admin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.oep.services.admin.response.oep_Response;
import com.oep.services.admin.response.oep_ResponseInfo;

public class oep_IDAO {
	@Autowired
	protected oep_Response inventoryResponseOBJ;
	@Autowired
	protected oep_ResponseInfo inventoryResponseInfoOBJ;
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
}
