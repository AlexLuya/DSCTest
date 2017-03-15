/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dsc.test.db.sql.Source;

import schemacrawler.schema.Schema;

/**
 * The Interface DataBase.
 *
 * @Author alex
 * @CreateTime Aug 11, 2016 10:07:35 PM
 * @Version 1.0
 * @Since 1.0
 */
public interface DataBase
{

	/**
	 * Close.
	 */
	void close();

	/**
	 * Connect.
	 *
	 * @param db
	 *            the db
	 * @param user
	 *            the user
	 * @param password
	 *            the password
	 * @return the data base
	 */
	DataBase connect(String db, String user, String password);

	/**
	 * Connection url.
	 *
	 * @return the string
	 */
	String connectionUrl();

	/**
	 *
	 */
	void ensureConnected();

	/**
	 * @param sql
	 *            any SQL statement
	 * @return updated row count
	 */
	int exec(String sql);

	/**
	 * Host.
	 *
	 * @param host
	 *            the host
	 * @return the data base
	 */
	DataBase host(String host);

	/**
	 * Insert.
	 *
	 * @param sql
	 *            the sql
	 * @param values
	 *            the values
	 * @deprecated replaced by {@link com.dsc.test.db.Table#insert()}.
	 */
	@Deprecated
	void insert(String sql, Object[][] values);

	/**
	 * Name of.
	 *
	 * @param schema
	 *            the schema
	 * @return the string
	 */
	String nameOf(Schema schema);

	/**
	 * Password.
	 *
	 * @return the string
	 */
	String password();

	/**
	 * Port.
	 *
	 * @param port
	 *            the port
	 * @return the data base
	 */
	DataBase port(int port);

	/**
	 * @param sql
	 * @return
	 */
	PreparedStatement prepareStatement(String sql);

	/**
	 * Query.
	 *
	 * @param query
	 *            the query
	 * @return the result set
	 */
	ResultSet query(String query);

	/**
	 * Query from source.
	 *
	 * @param query
	 *            the query
	 * @param string
	 * @return the result set
	 */
	ResultSet queryFromSource(String db, String query);

	/**
	 * Schema name.
	 *
	 * @return the string
	 */
	String schemaName();

	void setDefaultSourceDB(String sourceDB);

	void setSources(Source... dbTablePairs);

	/**
	 * Sets the source tables for current test.
	 *
	 * @param tables
	 *            the new source tables
	 */
	void setSourceTable(String table);

	/**
	 * @return
	 */
	Source[] sources();

	/**
	 * Table.
	 *
	 * @param table
	 *            the table
	 * @return the table
	 */
	Table table(String table);

	/**
	 * User.
	 *
	 * @return the string
	 */
	String user();
}
