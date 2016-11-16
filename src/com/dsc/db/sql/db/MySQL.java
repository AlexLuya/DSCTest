/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db.sql.db;

import schemacrawler.schema.Schema;

/**
 * @Author alex
 * @CreateTime Aug 19, 2016 4:38:23 PM
 * @Version 1.0
 * @Since 1.0
 */
public class MySQL extends MySQLBased
{
	public static MySQL get()
	{
		return new MySQL();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.dsc.db.sql.SQLDataBase#nameOf(schemacrawler.schema.Schema)
	 */
	@Override
	public String nameOf(Schema schema)
	{
		return schema.getCatalogName();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.sql.MySQLBased#driverClass()
	 */
	@Override
	protected String driverClass()
	{
		return "com.mysql.jdbc.Driver";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.sql.MySQLBased#type()
	 */
	@Override
	protected String type()
	{
		return "mysql";
	}
}