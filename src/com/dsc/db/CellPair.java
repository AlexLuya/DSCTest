/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db;

import com.dsc.test.base.Pair;

/**
 * @Author alex
 * @CreateTime Aug 21, 2016 4:39:35 PM
 * @Version 1.0
 * @Since 1.0
 */
public class CellPair extends Pair<Cell>
{
	private final String keyName;

	/**
	 * @param cell_1
	 * @param cell_2
	 */
	public CellPair(String keyName, Cell cell_1, Cell cell_2)
	{
		super(cell_1, cell_2);
		this.keyName = keyName;
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

	@Override
	public String toString()
	{
		if (left == null)
		{
			return String.format("\n   {%s=%s  column=%s  left=null  right=%s}", keyName, right.key(), right.column(), right.value());
		} else if (right == null)
		{
			return String.format("\n   {%s=%s  column=%s  left=%s  right=null}", keyName, left.key(), left.column(), left.value());
		}

		return String.format("\n   {%s=%s  column=%s  left=%s  right=%s}", keyName, left.key(), left.column(), left.value(),
				right.value());
	}
}