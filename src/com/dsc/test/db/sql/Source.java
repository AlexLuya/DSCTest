/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db.sql;

import com.dsc.common.Pair;

/**
 * @Author alex
 * @CreateTime Aug 31, 2016 2:14:22 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Source extends Pair<String>
{
	public final String	testCaseColumn		= "test_case";
	public String		primaryKeyColumn	= "id";

	public Source(String db, String table, String primaryKeyColumn)
	{
		this(db, table);
		this.primaryKeyColumn = primaryKeyColumn;
	}

	/**
	 * @param left
	 * @param right
	 */
	public Source(String db, String table)
	{
		super(db, table);
	}

	public String db()
	{
		return left;
	}

	public String table()
	{
		return right;
	}

	@Override
	public String toString()
	{
		return String.format("{DB=%s  Table=%s  PrimaryKeyColumn=%s  TestCaseColumn=%s}", db(), table(), primaryKeyColumn(),
				testCaseColumn());
	}

	/**
	 * @return
	 */
	public String primaryKeyColumn()
	{
		return primaryKeyColumn;
	}

	/**
	 * @return
	 */
	public String testCaseColumn()
	{
		return testCaseColumn;
	}
}