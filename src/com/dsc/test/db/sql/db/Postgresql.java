/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db.sql.db;

import com.dsc.test.db.DataBase;
import com.dsc.test.db.sql.SQLDataBase;
import com.dsc.util.Util;

/**
 * @Author alex
 * @CreateTime Aug 19, 2016 4:38:23 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Postgresql extends SQLDataBase
{
	public static Postgresql get()
	{
		return new Postgresql();
	}

	public Postgresql()
	{
		super(5432);
	}

	@Override
	public DataBase connect(String db, String user, String password)
	{
		Util.mustNotNullOrEmpty(name = db, "db");
		Util.mustNotNullOrEmpty(this.user = user, "user");
		Util.mustNotNullOrEmpty(this.password = password, "password");

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
		return String.format("jdbc:postgresql://%s:%d/%s", host, port, name);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.sql.SQLDataBase#schemaName()
	 */
	@Override
	public String schemaName()
	{
		//HP user may prefer put tables under other schemas
		return "public";
	}

	@Override
	protected String driverClass()
	{
		return "org.postgresql.Driver";
	}
}