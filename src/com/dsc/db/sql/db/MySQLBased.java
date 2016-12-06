/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db.sql.db;

import com.dsc.db.DataBase;
import com.dsc.db.sql.SQLDataBase;
import com.dsc.selenium.util.Util;

/**
 * @Author alex
 * @CreateTime Aug 19, 2016 4:38:23 PM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class MySQLBased extends SQLDataBase
{

	/**
	 * @param defaultPort
	 */
	public MySQLBased()
	{
		super(3306);
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
	 * @see com.dsc.db.sql.SQLDataBase#connectionUrl()
	 */
	@Override
	public String connectionUrl()
	{
		return String.format("jdbc:" + type() + "://%s:%d/%s?characterEncoding=utf8", host, port, name);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.sql.SQLDataBase#schemaName()
	 */
	@Override
	public String schemaName()
	{
		return name;
	}

	protected abstract String type();
}