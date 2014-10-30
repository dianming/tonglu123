package com.zht.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * TOMCAT JNDI连接池获取代理
 */
public class TomcatProvider implements IConnectionProvider {
	@Override
	public Connection getConnection(String sourceName) throws SQLException {
		try {
			Context ctx = new InitialContext();
			return ((DataSource) ctx.lookup("java:comp/env/" + sourceName))
					.getConnection();
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}
}