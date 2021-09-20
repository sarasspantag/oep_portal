package com.oep.services.admin.util;

import com.oep.services.admin.connection.SpringAccessJdbcImpl;

public class ConnectionUtil {
	private static  SpringAccessJdbcImpl springMYSQLDAO;
	
	public static  SpringAccessJdbcImpl getSpringMYSQLDAO() {
		return springMYSQLDAO;
	}

	public void setSpringMYSQLDAO(SpringAccessJdbcImpl springMYSQLDAO) {
		ConnectionUtil.springMYSQLDAO = springMYSQLDAO;
	}
}
