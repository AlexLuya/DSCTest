/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db.sql.server;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;

/**
 * @Author alex
 * @CreateTime Aug 19, 2016 12:11:28 PM
 * @Version 1.0
 * @Since 1.0
 */
public class HsqlServer
{
	public static final String DB="test";
	private final String	dbLocation	= "test/";
	private Server			server;

	protected Connection connection()
	{
		Connection conn = null;

		try
		{
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/"+DB, "SA", "");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return conn;
	}

	protected void start()
	{
		server = new org.hsqldb.Server();

		HsqlProperties props = new HsqlProperties();
		props.setProperty("server.database.0", "file:" + dbLocation + "testdb;");
		props.setProperty("server.dbname.0", "test");

		try
		{
			server.setProperties(props);
		} catch (Exception e)
		{
			return;
		}

		server.start();
	}

	protected void stop()
	{
		server.shutdown();
	}
}
