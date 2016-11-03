/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.dsc.db.sql.Schema;
import com.dsc.selenium.util.Util;
import com.google.common.collect.Lists;

/**
 * @Author alex
 * @CreateTime Aug 19, 2016 5:34:55 PM
 * @Version 1.0
 * @Since 1.0
 */
public class TableImpl implements Table
{
	private DataBase	dataBase;
	private String		primaryKey	= "id_in_source";

	private Schema		schema;

	/**
	 * @param dataBase
	 */
	public TableImpl(DataBase dataBase, Schema schema)
	{
		Util.requireNotNull(this.dataBase = dataBase, "dataBase");
		Util.requireNotNull(this.schema = schema, "schema");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.Table#charTypedColumns()
	 */
	@Override
	public Columns charTypedColumns()
	{
		Columns columns = new Columns(dataBase, this);

		for (Column column : columns())
		{
			if (column.isCharTyped())
			{
				columns.add(column);
			}
		}

		return columns.isEmpty() ? null : columns;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.Table#ensureColumn(java.lang.String)
	 */
	@Override
	public Column column(String name)
	{
		return new Column(dataBase, schema.column(name), this);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.Table#columns()
	 */
	@Override
	public Columns columns()
	{
		Columns columns = new Columns(dataBase, this);

		for (schemacrawler.schema.Column column : schema().getColumns())
		{
			columns.add(new Column(dataBase, schema.column(column), this));
		}

		return columns;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.Table#columns(java.lang.String[])
	 */
	@Override
	public Columns columns(String... columns)
	{
		Columns cls = new Columns(dataBase, this);

		for (String cl : columns)
		{
			cls.add(new Column(dataBase, schema.column(cl), this));
		}

		return cls;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.Table#columnsExcept(java.lang.String[])
	 */
	@Override
	public Columns columnsExcept(String... columns)
	{
		ResultSet rs = dataBase.query("SELECT * FROM " + schema.table());
		try
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			// The column count starts from 1
			List<String> excludeds = Lists.newArrayList(columns);
			List<String> remains = Lists.newArrayList();
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
			{
				if (!excludeds.contains(rsmd.getColumnName(i)))
				{
					remains.add(rsmd.getColumnName(i));
				}
			}

			return columns(remains.toArray(new String[remains.size()]));
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
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
	 * @see com.dsc.db.table#deleteRowById(java.lang.Object)
	 */
	@Override
	public boolean deleteById(Object id)
	{
		dataBase.ensureConnected();

		return dataBase.exec(String.format("DELETE FROM %s %s", name(),whereIdEquals(id)));
	}

	@Override
	public void ensureSchemaRetrieved()
	{
		schema.ensureTableRetrieved();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.Table#hasReferenceColumns()
	 */
	@Override
	public Columns hasReferenceColumns()
	{
		Columns columns = new Columns(dataBase, this);

		for (Column column : columns())
		{
			if (column.hasReferences())
			{
				columns.add(column);
			}
		}

		return columns.isEmpty() ? null : columns;
	}

	@Override
	public void insert(String sql, Object[][] values)
	{
		dataBase.ensureConnected();

		PreparedStatement stmt = null;

		try
		{
			stmt = dataBase.prepareStatement(sql);

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
	 * @see com.dsc.db.Table#name()
	 */
	@Override
	public String name()
	{
		return schema.table();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.Table#primaryKey()
	 */
	@Override
	public String primaryKey()
	{
		return primaryKey;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.Table#primaryKey(java.lang.String)
	 */
	@Override
	public Table primaryKey(String primaryKey)
	{
		this.primaryKey = primaryKey;
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.Table#selectBy(java.lang.String)
	 */
	@Override
	public ResultSet selectBy(String sql)
	{
		dataBase.ensureConnected();

		return dataBase.query(sql);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.db.Table#selectById(java.lang.Object)
	 */
	@Override
	public ResultSet selectById(Object id)
	{
		return selectBy(String.format("select * FROM %s %s", name(),whereIdEquals(id)));
	}

	// HP override other methods
	@Override
	public String toString()
	{
		return name();
	}

	/**
	 * @return
	 */
	private schemacrawler.schema.Table schema()
	{
		return schema.ensureTableRetrieved();
	}

	private String whereIdEquals(Object id)
	{
		String whereIdEquals = "WHERE id=";

		if (id instanceof Integer)
		{
			return whereIdEquals + (int) id;
		} else if (id instanceof Long)
		{
			return whereIdEquals + (long) id;
		} else if (id instanceof String)
		{
			return whereIdEquals + "'" + (String) id + "'";
		} else
		{
			throw new RuntimeException(
					String.format("%s's type is %s that isn't a supported type:int/Integer,long/Long,String typed id supported,",
							id.toString(), id.getClass().getSimpleName()));
		}
	}
}
