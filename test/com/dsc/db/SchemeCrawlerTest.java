/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.google.common.collect.Lists;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schema.View;
import schemacrawler.schemacrawler.DatabaseConnectionOptions;
import schemacrawler.schemacrawler.ExcludeAll;
import schemacrawler.schemacrawler.IncludeAll;
import schemacrawler.schemacrawler.RegularExpressionInclusionRule;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.utility.SchemaCrawlerUtility;

public final class SchemeCrawlerTest
{

	public static void main(final String[] args) throws Exception
	{
		postgres();
		//		mariadb();
	}

	public static void mariadb() throws Exception
	{
		// Create a database connection
		final DataSource dataSource = new DatabaseConnectionOptions("jdbc:mariadb://localhost:3306/test");
		final Connection connection = dataSource.getConnection("root", "112600827");

		// Create the options
		final SchemaCrawlerOptions options = new SchemaCrawlerOptions();
		options.setSchemaInfoLevel(SchemaInfoLevelBuilder.standard());

		options.setTableTypes(Lists.newArrayList("BASE TABLE", "TABLE", "VIEW", "UNKNOWN"));
		options.setRoutineInclusionRule(new ExcludeAll());
		// options.setSchemaInclusionRule(new IncludeAll());
		options.setSchemaInclusionRule(new RegularExpressionInclusionRule("test"));
		// options.setTableNamePattern("%");
		// options.setColumnInclusionRule(new IncludeAll());
		// options.setRoutineColumnInclusionRule(new IncludeAll());
		options.setTableInclusionRule(new IncludeAll());

		// Get the schema schema
		final Catalog catalog = SchemaCrawlerUtility.getCatalog(connection, options);

		for (final Schema schema : catalog.getSchemas())
		{
			System.out.print("c--> " + schema.getCatalogName() + "\n");
			if (!schema.getCatalogName().equals("assessment"))
			{
				continue;
			}

			for (final Table table : catalog.getTables(schema))
			{
				System.out.print("o--> " + table);
				if (table instanceof View)
				{
					System.out.println(" (VIEW)");
				} else
				{
					System.out.println();
				}

				for (final Column column : table.getColumns())
				{
					System.out.println("     o--> " + column + " (" + column.getColumnDataType() + ")");
				}
			}
		}
	}

	/**
	 * @throws SchemaCrawlerException
	 * @throws SQLException
	 */
	private static void postgres() throws SchemaCrawlerException, SQLException
	{
		// Create a database connection
		final DataSource dataSource = new DatabaseConnectionOptions("jdbc:postgresql://localhost:5432/test");
		final Connection connection = dataSource.getConnection("postgres", "112600827");

		// Create the options
		final SchemaCrawlerOptions options = new SchemaCrawlerOptions();
		options.setSchemaInfoLevel(SchemaInfoLevelBuilder.standard());

		options.setTableTypes(Lists.newArrayList("BASE TABLE", "TABLE", "VIEW", "UNKNOWN"));
		options.setRoutineInclusionRule(new ExcludeAll());
		options.setSchemaInclusionRule(new IncludeAll());
		options.setTableInclusionRule(new IncludeAll());

		// Get the schema schema
		final Catalog catalog = SchemaCrawlerUtility.getCatalog(connection, options);

		for (final Schema schema : catalog.getSchemas())
		{
			System.out.print("c--> " + schema.getName() + "\n");

			for (final Table table : catalog.getTables(schema))
			{
				System.out.print("o--> " + table);
				if (table instanceof View)
				{
					System.out.println(" (VIEW)");
				} else
				{
					System.out.println();
				}

				for (final Column column : table.getColumns())
				{
					System.out.println("     o--> " + column + " (" + column.getColumnDataType() + ")");
				}
			}
		}
	}
}