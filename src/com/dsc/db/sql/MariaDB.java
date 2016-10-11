/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db.sql;


/**
 * @Author alex
 * @CreateTime Aug 19, 2016 4:38:23 PM
 * @Version 1.0
 * @Since 1.0
 */
public class MariaDB extends MySQLBased
{
	public static MariaDB get()
	{
		return new MariaDB();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.sql.MySQLBased#driverClass()
	 */
	@Override
	protected String driverClass()
	{
		return "org.mariadb.jdbc.Driver";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.sql.MySQLBased#type()
	 */
	@Override
	protected String type()
	{
		return "mariadb";
	}

}