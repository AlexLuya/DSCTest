/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db;

import com.dsc.util.common.Pair;

/**
 * @Author alex
 * @CreateTime Aug 24, 2016 3:47:07 PM
 * @Version 1.0
 * @Since 1.0
 */
public class RowPair extends Pair<Row>
{
	private Object			idInSource	= null;
	private final String	keyName;
	private String			testCaseName;

	public RowPair(Row left, Row right, String keyName)
	{
		super(left, right);
		this.keyName = keyName;
	}

	public Row availabeItem()
	{
		if (left == null)
		{
			return right;
		}

		return left;
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

	public Object key()
	{
		return availabeItem().key();
	}

	/**
	 * @param idInSource2
	 * @return
	 */
	public boolean keyEqualsTo(Object key)
	{
		return availabeItem().keyEqualsTo(key);
	}

	/**
	 * @param string
	 */
	public void setTestCaseName(String testCase)
	{
		this.testCaseName = testCase;
	}

	/**
	 * @return test case name
	 */
	public String testCase()
	{
		return testCaseName;
	}

	@Override
	public String toString()
	{
		if (left == null)
		{
			return String.format("\n   {testCase=%s  left=null  right=%s}", testCaseName, right.summary());
		} else if (right == null)
		{
			return String.format("\n   {testCase=%s  left=%s  right=null}", testCaseName, left.summary());
		}

		return String.format("\n   {%s=%s  testCase=%s  diffs=[%s]}", keyName, left.key(), testCaseName, diffs());
		// return String.format("{%s=%s testCase=%s diffs={%s} left=%s
		// right=%s}", keyName, left.key(), testCase, diffs(),
		// left.toString(), right.toString());
	}

	private String diffs()
	{
		String diffs = "";

		for (Cell leftCell : left.cells)
		{
			Cell rightCell = right.correspondedCell(leftCell);

			if (!leftCell.equals(rightCell))
			{
				diffs = diffs + String.format("%s:(%s vs %s)  ", leftCell.column(), prettify(leftCell.stringifiedValue()),
						prettify(stringified(rightCell)));
			}
		}

		return diffs.trim();
	}

	private String prettify(String value)
	{
		if ("".equals(value))
		{
			return "\"\"";
		}

		if (value == null)
		{
			return "null";
		}

		return value;
	}

	/**
	 * @param cell
	 * @return
	 */
	private String stringified(Cell cell)
	{
		if (cell == null)
		{
			return null;
		}

		return cell.stringifiedValue();
	}
}