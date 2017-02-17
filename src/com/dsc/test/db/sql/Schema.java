/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db.sql;

import java.sql.Connection;

import com.dsc.test.db.DataBase;
import com.google.common.collect.Lists;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.DatabaseConnectionOptions;
import schemacrawler.schemacrawler.ExcludeAll;
import schemacrawler.schemacrawler.IncludeAll;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.utility.SchemaCrawlerUtility;

/**
 * @Author alex
 * @CreateTime Aug 25, 2016 3:46:39 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Schema
{
	private Catalog						catalog;
	private String						columnName;
	private Column						columnSchema;
	private final DataBase				dataBase;
	private schemacrawler.schema.Schema	dataBaseSchema;
	private String						tableName;
	private Table						tableSchema;

	/**
	 * @param dataBase
	 */
	public Schema(DataBase dataBase)
	{
		this.dataBase = dataBase;
	}

	public Schema(DataBase dataBase, String table)
	{
		this.dataBase = dataBase;
		this.tableName = table;
	}

	public Schema(DataBase dataBase, String table, Column columnSchema)
	{
		this(dataBase, table, columnSchema.getName());
		this.columnSchema = columnSchema;
	}

	public Schema(DataBase dataBase, String table, String columnName)
	{
		this(dataBase, table);
		this.columnName = columnName;
	}

	/**
	 * @return
	 */
	public String column()
	{
		return columnName.toLowerCase();
	}

	public Schema column(Column column)
	{
		return new Schema(dataBase, tableName, column);
	}

	/**
	 * @param cl
	 * @return
	 */
	public Schema column(String cl)
	{
		return new Schema(dataBase, tableName, cl);
	}

	/**
	 * @return
	 */
	public Column ensureColumnRetrieved()
	{
		if (columnSchema != null)
		{
			return columnSchema;
		}

		ensureTableRetrieved();

		for (Column cl : tableSchema.getColumns())
		{
			if (columnName.equals(cl.getName()))
			{
				return columnSchema = cl;
			}
		}

		throw new RuntimeException("column:" + columnName + " not existed");
	}

	/**
	 * @return
	 */
	public schemacrawler.schema.Schema ensureDBRetrieved()
	{
		if (dataBaseSchema != null)
		{
			return dataBaseSchema;
		}

		ensureCatalogRetrieved();

		for (schemacrawler.schema.Schema schema : catalog.getSchemas())
		{
			if (dataBase.nameOf(schema).equals(dataBase.schemaName()))
			{
				return dataBaseSchema = schema;
			}
		}

		throw new RuntimeException("Schema:" + dataBase.schemaName() + " not existed");
	}

	/**
	 * @return
	 */
	public Table ensureTableRetrieved()
	{
		if (tableSchema != null)
		{
			return tableSchema;
		}

		ensureDBRetrieved();

		for (schemacrawler.schema.Table table : catalog.getTables(dataBaseSchema))
		{
			if (tableName.equals(table.getName().toLowerCase()))
			{
				return tableSchema = table;
			}
		}

		throw new RuntimeException("table:" + tableName + " not existed");
	}

	/**
	 * @return
	 */
	public String table()
	{
		return tableName;
	}

	/**
	 * @return
	 */
	public Schema table(String table)
	{
		return new Schema(dataBase, table);
	}

	/**
	 * @return
	 *
	 */
	private Catalog ensureCatalogRetrieved()
	{
		if (catalog != null)
		{
			return catalog;
		}

		return catalog = retrieveCatalog();
	}

	/**
	 * @return
	 */
	private Catalog retrieveCatalog()
	{
		// Create the options
		final SchemaCrawlerOptions options = new SchemaCrawlerOptions();

		options.setSchemaInfoLevel(SchemaInfoLevelBuilder.standard());
		options.setTableTypes(Lists.newArrayList("BASE TABLE", "TABLE", "VIEW", "UNKNOWN"));
		options.setRoutineInclusionRule(new ExcludeAll());
		options.setSchemaInclusionRule(new IncludeAll());
		// options.setSchemaInclusionRule(new
		// RegularExpressionInclusionRule("test"));
		options.setTableInclusionRule(new IncludeAll());

		try (Connection conn = new DatabaseConnectionOptions(dataBase.connectionUrl()).getConnection(dataBase.user(),
				dataBase.password()))
		{
			return SchemaCrawlerUtility.getCatalog(conn, options);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}