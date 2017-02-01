/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db.sql.db;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;

import com.dsc.test.db.DataBase;
import com.dsc.test.db.sql.SQLDataBase;
import com.dsc.test.web.util.Util;

/**
 * @Author alex
 * @CreateTime Aug 19, 2016 4:38:23 PM
 * @Version 1.0
 * @Since 1.0
 */
public class HsqlDB extends SQLDataBase
{
	public static HsqlDB get()
	{
		return new HsqlDB();
	}

	private final String	dbLocation	= "test/";

	private Server			server;

	public HsqlDB()
	{
		super(9001);
	}

	public DataBase connect(String db)
	{
		return connect(db, null, null);
	}

	@Override
	public DataBase connect(String db, String user, String password)
	{
		Util.mustNotNullOrEmpty(name = db, "db");
		// Util.tryToThrowOutNullOrEmptyArgumentException(this.user=user,
		// "user");
		// Util.tryToThrowOutNullOrEmptyArgumentException(this.password=password,
		// "password");

		return doConnect();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.sql.SQLDataBase#connectionUrl()
	 */
	@Override
	public String connectionUrl()
	{
		return "jdbc:hsqldb:hsql://localhost/" + name;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.sql.SQLDataBase#schemaName()
	 */
	@Override
	public String schemaName()
	{
		return "PUBLIC";
	}

	public void start()
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
			throw new RuntimeException(e);
		}

		server.start();
	}

	public void stop()
	{
		server.shutdown();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.sql.SQLDataBase#doConnect()
	 */
	@Override
	protected String driverClass()
	{
		return "org.hsqldb.jdbcDriver";
	}
}