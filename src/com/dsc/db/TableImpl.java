/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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

	@Override
	public void ensureSchemaRetrieved(){
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
}
