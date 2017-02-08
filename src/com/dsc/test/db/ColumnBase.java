/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db;

import static com.dsc.util.Log.info;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dsc.test.db.sql.Schema;
import com.dsc.util.Util;
import com.google.common.collect.Lists;

/**
 * @Author alex
 * @CreateTime Aug 11, 2016 5:27:40 PM
 * @Version 1.0
 * @Since 1.0
 */
public class ColumnBase<T extends ColumnBase<T>>
{
	protected List<Cell>	cells;
	protected DataBase		dataBase;
	protected final Table	parent;
	protected Schema		schema;

	/**
	 * @param dataBase
	 * @param schema
	 */
	public ColumnBase(DataBase dataBase, Schema schema, Table parent)
	{
		Util.mustNotNull(this.dataBase = dataBase, "dataBase");
		Util.mustNotNull(this.schema = schema, "schema");
		Util.mustNotNull(this.parent = parent, "parent");
	}

	public Cell cell(int index)
	{
		return cells.get(index);
	}

	/**
	 * @return
	 */
	public String dataType()
	{
		return schema().getColumnDataType().getName().toLowerCase();
	}

	public String defaultValue()
	{
		return schema().getDefaultValue();
	}

	public List<CellPair> diffsWith(T toCompare)
	{
		Util.mustNotNull(toCompare, "toCompare");

		ensureRetrieved();

		List<CellPair> diffs = findDiffs(toCompare);

		//
		diffs = merge(diffs, Util.swap(toCompare.findDiffs(this)));

		return diffs.isEmpty() ? null : diffs;
	}

	public ColumnBase<T> ensureRetrieved()
	{
		if (cells == null)
		{
			retrieveValues();
		}

		return this;
	}

	public boolean hasValue(String value)
	{

		Util.mustNotNullOrEmpty(value, "value");

		ensureRetrieved();

		for (Cell cell : cells)
		{
			if (value.equals(cell.value()))
			{
				return true;
			}
		}

		info("column %s's values are %s", name(), stringifiedValues());

		return false;
	}

	public boolean isCharTyped()
	{
		return "character".equals(dataType()) || "char".equals(dataType()) || "varchar".equals(dataType());
	}

	public String name()
	{
		return schema.column();
	}

	public boolean nullable()
	{
		return schema().isNullable();
	}

	public int rowCount()
	{
		ensureRetrieved();

		return cells.size();
	}

	public String stringifiedValues()
	{
		String values = "";

		if (cells == null)
		{
			return values;
		}

		for (Cell c : cells)
		{
			values = values + String.format("%s=%s", c.key(), c.value()) + " ";
		}

		return values.trim();
	}

	// HP override hash() and other methods
	@Override
	public String toString()
	{
		return String.format("{column:%s cells:[%s]}", name(), stringifiedValues());
	}

	/**
	 * @param min
	 * @param max
	 * @return
	 */
	public boolean valuesAreBetween(int min, int max)
	{
		// NP- Auto-generated method stub
		return false;
	}

	/**
	 * @param column
	 * @return
	 */
	List<CellPair> findDiffs(ColumnBase<T> column)
	{
		column.ensureRetrieved();

		List<CellPair> diffs = Lists.newArrayList();

		for (int i = 0; i < column.rowCount(); i++)
		{
			Cell c = cellByKey(column.cell(i).key());

			if (!column.cell(i).equals(c))
			{
				diffs.add(new CellPair(parent.primaryKey(), c, column.cell(i)));
			}
		}

		return diffs;
	}

	/**
	 * @return
	 */
	protected schemacrawler.schema.Column schema()
	{
		return schema.ensureColumnRetrieved();
	}

	private Cell cellByKey(Object key)
	{
		for (Cell c : cells)
		{
			if (c.keyEqualsTo(key))
			{
				return c;
			}
		}

		return null;
	}

	private List<CellPair> merge(List<CellPair> list_1, List<CellPair> list_2)
	{

		for (CellPair cp : list_2)
		{
			if (notContains(list_1, cp))
			{
				list_1.add(cp);
			}
		}

		return list_1;
	}

	private boolean notContains(List<CellPair> list, CellPair item)
	{

		for (CellPair cp : list)
		{
			if (cp.equals(item))
			{
				return false;
			}
		}

		return true;
	}

	private String primaryKey()
	{
		// HP please refer to bug:
		// HP use schema will cause heavy performance penalty
		return "id";
		// return table().getPrimaryKey().getName();
	}

	/**
	 *
	 */
	private void retrieveValues()
	{
		cells = Lists.newArrayList();

		// NP caution
		ResultSet rs = dataBase
				.query(String.format("SELECT %s,%s FROM %s ORDER BY %s ASC", primaryKey(), name(), tableName(), name()));

		try
		{
			// save rows as cells
			while (rs.next())
			{
				cells.add(new Cell(name(), rs.getObject(1), rs.getObject(2)));
			}

		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			if (rs == null)
			{
				return;
			}

			try
			{
				rs.close();
			} catch (SQLException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * @return
	 */
	private String tableName()
	{
		return schema.table();
	}
}