/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.dsc.test.db.DataBase;
import com.dsc.test.db.Table;
import com.dsc.test.web.util.Util;

/**
 * @Author alex
 * @CreateTime Aug 19, 2016 4:33:39 PM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class SQLDataBase implements DataBase
{
	protected Connection	conn;
	protected String		host;
	protected String		name;
	protected String		password;
	protected int			port;
	protected final Schema	schema;
	protected String		user;
	private String			defaultSourceDB;
	private Source[]		sources;

	/**
	 *
	 */
	public SQLDataBase(int port)
	{
		this.host = "localhost";

		if (port <= 0)
		{
			throw new IllegalArgumentException("port must not <= 0,but it is " + port);
		}

		this.port = port;
		schema = new Schema(this);
	}

	@Override
	public void close()
	{
		try
		{
			conn.close();
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 *
	 */
	@Override
	public void ensureConnected()
	{
		try
		{
			if (!conn.isClosed())
			{
				return;
			}
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}

		doConnect();
	}

	@Override
	public int exec(String sql)
	{
		// validate argument
		Util.mustNotNullOrEmpty(sql, "sql");

		ensureConnected();

		Statement stmt =null;

		try
		{
			stmt = conn.createStatement();
			stmt.execute(sql);
			return stmt.getUpdateCount();
		}catch (Exception e)
		{
			throw new RuntimeException(e.getMessage()+"...."+sql);
		} finally{
			if(stmt!=null)
			{
				try
				{
					stmt.close();
				} catch (SQLException e)
				{
					throw new RuntimeException(e);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.DataBase#host(java.lang.String)
	 */
	@Override
	public DataBase host(String host)
	{
		Util.mustNotNullOrEmpty(host, "host");

		this.host = host;

		return this;
	}

	/**
	 * @deprecated replaced by {@link com.dsc.test.db.sql.SQLTableImpl#insert()}.
	 */
	@Deprecated
	@Override
	public void insert(String sql, Object[][] values)
	{
		ensureConnected();

		PreparedStatement stmt = null;

		try
		{
			stmt = prepareStatement(sql);

			for (Object[] tupe : values)
			{
				for (int i = 0; i < tupe.length; i++)
				{
					Object value = tupe[i];
					int l = i + 1;

					if (value instanceof Double)
					{
						stmt.setDouble(l, (double) value);
					} else if (value instanceof Float)
					{
						stmt.setFloat(l, (float) value);
					} else if (value instanceof Integer)
					{
						stmt.setInt(l, (int) value);
					} else if (value instanceof Long)
					{
						stmt.setLong(l, (long) value);
					} else if (value instanceof String)
					{
						stmt.setString(l, (String) value);
					} else if (value instanceof Timestamp)
					{
						stmt.setTimestamp(l, (Timestamp) value);
					} else
					{
						throw new RuntimeException(value + " hasn't be handled for batch insertion statement preparing");
					}
				}

				//
				stmt.addBatch();
			}

			stmt.executeBatch();

		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			try
			{
				stmt.close();
			} catch (SQLException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.dsc.test.db.sql.SQLDataBase#nameOf(schemacrawler.schema.Schema)
	 */
	@Override
	public String nameOf(schemacrawler.schema.Schema schema)
	{
		return schema.getName();
	}

	@Override
	public String password()
	{
		return password;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.DataBase#host(java.lang.String)
	 */
	@Override
	public DataBase port(int port)
	{
		if (port <= 0)
		{
			throw new IllegalArgumentException("port: " + port + " is invalid and it MUST NOT <= 0");
		}

		this.port = port;

		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.DataBase#prepareStatement(java.lang.String)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql)
	{
		// validate argument
		Util.mustNotNullOrEmpty(sql, "sql");

		PreparedStatement stmt=null;

		try
		{
			stmt=conn.prepareStatement(sql);
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}

		return stmt;
	}

	@Override
	public ResultSet query(String query)
	{
		// validate argument
		Util.mustNotNullOrEmpty(query, "query");

		ensureConnected();

		try
		{
			// query and return result
			return conn.createStatement().executeQuery(query);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResultSet queryFromSource(String sourceDB, String query)
	{
		// close main db
		close();

		// switch to alternative db
		String expectedDataDB = name;
		name = sourceDB;

		// connect to alternative db
		ensureConnected();

		// query from alternative db
		ResultSet rs = query(query);

		// close connection to alternative db
		close();

		// switch base to expected data db
		name = expectedDataDB;

		return rs;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.DataBase#setSourceDB(java.lang.String)
	 */
	@Override
	public void setDefaultSourceDB(String defaultSourceDB)
	{
		Util.mustNotNullOrEmpty(this.defaultSourceDB = defaultSourceDB, "default source db");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.DataBase#setSourceTables(java.lang.String[])
	 */
	@Override
	public void setSources(Source... sources)
	{
		Util.mustNotNull("sources", this.sources = sources);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.DataBase#setSourceTables(java.lang.String,
	 * java.lang.String[])
	 */
	@Override
	public void setSourceTable(String sourceTable)
	{
		Util.mustNotNullOrEmpty(sourceTable, "sourceTable");
		sources = new Source[] { new Source(defaultSourceDB, sourceTable) };
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.DataBase#sourceTables()
	 */
	@Override
	public Source[] sources()
	{
		return sources;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.db.DataBase#table(java.lang.String)
	 */
	@Override
	public Table table(String table)
	{
		return new SQLTableImpl(this, schema.table(table));
	}

	@Override
	public String user()
	{
		return user;
	}

	/**
	 *
	 */
	protected DataBase doConnect() throws RuntimeException
	{
		try
		{
			Class.forName(driverClass()).newInstance();
			conn = DriverManager.getConnection(connectionUrl(), user, password);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}

		return this;
	}

	/**
	 * @return
	 */
	protected abstract String driverClass();
}