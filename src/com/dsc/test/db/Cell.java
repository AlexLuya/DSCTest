/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db;

import com.dsc.test.web.util.Util;

/**
 * @Author alex
 * @CreateTime Aug 11, 2016 5:24:40 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Cell
{
	private String	column;
	private Object	key;
	private Object	value;

	/**
	 * @param value
	 */
	public Cell(String column, Object key, Object value)
	{
		Util.mustNotNull(this.column = column, "column");
		Util.mustNotNull(this.key = key, "key");
		this.value = value;
	}

	public String column()
	{
		return column;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		return toString().equals(obj.toString());
	}

	/**
	 * @return
	 */
	public Object key()
	{
		return key;
	}

	/**
	 * @param key
	 * @return
	 */
	public boolean keyEqualsTo(Object key)
	{
		Util.mustNotNull(key, "key");

		// toString() to fix the bug: Integer-41167 != Long-41167
		return this.key.toString().equals(key.toString());
	}

	/**
	 * @param toCompare
	 * @return
	 */
	public boolean sameKeyAndColumnWith(Cell toCompare)
	{
		return column.equals(toCompare.column()) && keyEqualsTo(toCompare.key());
	}

	public String stringifiedValue()
	{
		if (value == null)
		{
			return null;
		}

		if (value instanceof String)
		{
			return "'" + value.toString() + "'";
		}

		return value.toString();
	}

	@Override
	public String toString()
	{
		if (value == null)
		{
			return String.format("%s=null", column);
		}

		return String.format("%s=%s", column, value.toString().trim());
	}

	/**
	 * get the cell value.
	 *
	 * @return value
	 */
	public Object value()
	{
		return value;
	}

	/**
	 * @param cell
	 * @return
	 */
	public boolean valueNotEqualsWith(Cell cell)
	{
		if (value == null)
		{
			return value != cell.value();
		}

		return !value.equals(cell.value);
	}
}