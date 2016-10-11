/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db;

import java.util.List;

import com.dsc.selenium.util.Util;

/**
 * @Author alex
 * @CreateTime Aug 24, 2016 3:50:36 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Row
{
	final List<Cell>	cells;
	private Object		key;
	private String		keyName;

	/**
	 * @param content
	 */
	public Row(String keyName, Object key, List<Cell> cells)
	{
		Util.requireNotNull(this.key = key, "key");
		Util.requireNotNull(this.keyName = keyName, "keyName");
		this.cells = cells;
	}

	// /**
	// * @param idInSource
	// * @return
	// */
	// public Cell cellByColumn(String column)
	// {
	// for (Cell cell : cells)
	// {
	// if (cell.column().equals(column))
	// {
	// return cell;
	// }
	// }
	//
	// return null;
	// }

	/**
	 * @param key2
	 * @return
	 */
	public Cell correspondedCell(Cell toCompare)
	{
		for (Cell cell : cells)
		{
			if (cell.sameKeyAndColumnWith(toCompare))
			{
				return cell;
			}
		}

		return null;
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
		Util.requireNotNull(key, "key");

		// toString() to fix the bug: Integer-41167 != Long-41167
		return this.key.toString().equals(key.toString());
	}

	public String summary()
	{
		return String.format("(%s:%s,...)", keyName, key);
	}

	@Override
	public String toString()
	{
		String str = "";

		for (Cell cell : cells)
		{
			str = str + cell.toString() + " ";
		}

		return String.format("(%s:%s  %s)", keyName, key, str);
	}
}
