/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db;

import static java.lang.String.format;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dsc.db.sql.Source;
import com.dsc.selenium.util.Util;
import com.google.common.collect.Lists;

/**
 * @Author alex
 * @CreateTime Aug 19, 2016 5:25:43 PM
 * @Version 1.0
 * @Since 1.0
 */

public class Columns extends ArrayList<Column>
{
	private static final long	serialVersionUID	= 1L;
	protected final DataBase	dataBase;
	private List<Row>			rows;
	private final Table			table;

	/**
	 * @param dataBase
	 */
	public Columns(DataBase dataBase, Table table)
	{
		this.dataBase = dataBase;
		this.table = table;
	}

	public List<RowPair> diffsWith(Columns toCompare) throws SQLException
	{
		Util.mustNotNull(toCompare, "toCompare");

		ensureRetrieved();

		List<RowPair> diffs = findDiffs(toCompare);

		//
		diffs = merge(diffs, Util.swap(toCompare.findDiffs(this)));

		return diffs.isEmpty() ? null : diffsWithTestCaseName(diffs);
	}

	public List<String> ensureExisted(String... columns)
	{
		List<String> notExisteds = Lists.newArrayList();

		for (String column : columns)
		{
			if (notExisted(column))
			{
				notExisteds.add(column);
			}
		}

		return notExisteds.isEmpty() ? null : notExisteds;
	}

	public boolean ensureNotExist(String... columns)
	{
		return ensureExisted(columns) == null;
	}

	public List<Row> ensureRetrieved()
	{
		if (rows != null)
		{
			return rows;
		}

		return doRetrieve();
	}

	public Column itemByName(String name)
	{

		for (Column column : this)
		{
			if (name.equals(column.name()))
			{
				return column;
			}
		}

		return null;
	}

	public List<String> nullables()
	{
		List<String> nullables = Lists.newArrayList();

		for (Column column : this)
		{
			if (column.nullable())
			{
				nullables.add(column.name());
			}
		}

		return nullables.isEmpty() ? null : nullables;
	}

	public int rowCount()
	{
		ensureRetrieved();

		return rows.size();
	}

	@Override
	public String toString()
	{
		return "[" + columnNames() + "]";
	}

	List<RowPair> findDiffs(Columns columns)
	{
		columns.ensureRetrieved();

		List<RowPair> diffs = Lists.newArrayList();

		for (int i = 0; i < columns.rowCount(); i++)
		{
			Row r = rowByKey(columns.row(i).key());

			// same keyed row comparison or r==null
			if (!columns.row(i).equals(r))
			{
				diffs.add(new RowPair(r, columns.row(i), table.primaryKey()));
			}
		}

		return diffs;
	}

	/**
	 * @param diff
	 * @return
	 */
	// private Row availableItem(RowPair diff)
	// {
	// if (diff.left == null)
	// {
	// return diff.right;
	// }
	//
	// return diff.left;
	// }

	/**
	 * @param i
	 * @return
	 */
	private String columnName(int i)
	{
		return get(i).name();
	}

	/**
	 * @return
	 */
	private String columnNames()
	{
		String names = "";

		for (Column cl : this)
		{
			names = names + cl.name() + ",";
		}

		return names.substring(0, names.length() - 1);
	}

	private RowPair diffBySourceId(List<RowPair> diffs, Object idInSource)
	{

		for (RowPair diff : diffs)
		{
			if (diff.keyEqualsTo(idInSource))
			{
				return diff;
			}
		}

		return null;
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	private List<RowPair> diffsWithTestCaseName(List<RowPair> diffs) throws SQLException
	{
		if (dataBase.sources() == null)
		{
			// set to query same name table from source database
			dataBase.setSourceTable(table.name());
		}

		Source[] sources = dataBase.sources();
		for (Source source : sources)
		{
			String idsOfDiffWithouTestCase = idsOfDiffWithoutTestCase(diffs);

			if (idsOfDiffWithouTestCase != null)
			{
				setTestCaseName(diffs, idsOfDiffWithouTestCase, source);
			}
		}

		return diffs;
	}

	/**
	 *
	 */
	private List<Row> doRetrieve()
	{
		rows = Lists.newArrayList();

		// NP caution
		ResultSet rs = dataBase.query(format("SELECT %s,%s FROM %s ORDER BY %s ASC", table.primaryKey(), columnNames(),
				table.name(), table.primaryKey()));

		try
		{
			// save rows as rows
			while (rs.next())
			{
				rows.add(new Row(table.primaryKey(), rs.getObject(1), toCells(rs)));
			}

		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			if (rs == null)
			{
				return null;
			}

			try
			{
				rs.close();
			} catch (SQLException e)
			{
				throw new RuntimeException(e);
			}
		}

		return rows;
	}

	private String idsOfDiffWithoutTestCase(List<RowPair> diffs)
	{
		String joined = "";

		for (RowPair diff : diffs)
		{
			if (diff.testCase() != null)
			{
				continue;
			}

			// Object primaryKey = keyValue(diff);

			if (diff.key() instanceof String)
			{
				joined = joined + "'" + diff.key() + "',";
			} else
			{
				joined = joined + diff.key() + ",";
			}
		}

		if ("".equals(joined))
		{
			return null;
		}

		return joined.substring(0, joined.length() - 1);
	}

	/**
	 * @param diff
	 * @return
	 */
	// private long keyValue(RowPair diff)
	// {
	// long primaryKey;
	// if (availableItem(diff).key() instanceof Long)
	// {
	// primaryKey = (long) availableItem(diff).key();
	// } else
	// {
	// primaryKey = (int) availableItem(diff).key();
	// }
	// return primaryKey;
	// }

	private List<RowPair> merge(List<RowPair> list_1, List<RowPair> list_2)
	{

		for (RowPair cp : list_2)
		{
			if (notContains(list_1, cp))
			{
				list_1.add(cp);
			}
		}

		return list_1;
	}

	private boolean notContains(List<RowPair> list, RowPair item)
	{

		for (RowPair cp : list)
		{
			if (cp.equals(item))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * @param columnName
	 */
	private boolean notExisted(String columnName)
	{
		for (Column column : this)
		{
			if (columnName.equals(column.name()))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * @param i
	 * @return
	 */
	private Row row(int i)
	{
		ensureRetrieved();
		return rows.get(i);
	}

	private Row rowByKey(Object key)
	{
		for (Row r : rows)
		{
			if (r.keyEqualsTo(key))
			{
				return r;
			}
		}

		return null;
	}

	/**
	 * @param diffs
	 * @param idsOfDiffWithoutTestCaseName
	 * @param sourceTable
	 * @throws SQLException
	 */
	private void setTestCaseName(List<RowPair> diffs, String idsOfDiffWithoutTestCaseName, Source source) throws SQLException
	{
		@SuppressWarnings("resource")
		ResultSet rs = dataBase.queryFromSource(source.db(),
				format("SELECT %s,%s FROM %s WHERE %s in (%s)", source.primaryKeyColumn(), source.testCaseColumn(),
						source.table(), source.primaryKeyColumn(), idsOfDiffWithoutTestCaseName));

		while (rs.next())
		{
			diffBySourceId(diffs, rs.getObject(1)).setTestCaseName(rs.getString(2));
		}

		rs.close();
	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<Cell> toCells(ResultSet rs) throws SQLException
	{
		List<Cell> cells = Lists.newArrayList();

		// save rows as cells
		for (int i = 0; i < size(); i++)
		{
			cells.add(new Cell(columnName(i), rs.getObject(1), rs.getObject(i + 2)));
		}

		return cells;
	}
}