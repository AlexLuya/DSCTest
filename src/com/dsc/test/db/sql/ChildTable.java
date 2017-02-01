/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db.sql;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @Author alex
 * @CreateTime Dec 21, 2016 1:40:03 PM
 * @Version 1.0
 * @Since 1.0
 */
public class ChildTable
{
	public class Info
	{

		public String	foreignKey;

		public String	refereeColumn;

		public String	table;

		/**
		 * @param foreignKey
		 * @param table
		 */
		private Info(String table, String foreignKey)
		{
			this("id", table, foreignKey);
		}

		/**
		 * @param refereeColumn
		 * @param foreignKey
		 * @param table
		 */
		private Info(String refereeColumn, String table, String foreignKey)
		{
			this.refereeColumn = refereeColumn;
			this.foreignKey = foreignKey;
			this.table = table;
		}
	}

	/**
	 * @param table
	 * 		the table name
	 * @param foreignKey
	 * 		the column used to refer to parent table
	 * @description referee column that foreign key refers to will be "id" by
	 *              default
	 */
	public static ChildTable from(String table, String foreignKey)
	{
		return new ChildTable(table, foreignKey);
	}

	/**
	 * @param refereeColumn
	 * 		the column in parent table that foreign key refers to
	 * @param table
	 * 		the table name
	 * @param foreignKey
	 * 		the column used to refer to parent table
	 */
	public static ChildTable from(String refereeColumn, String table, String foreignKey)
	{
		return new ChildTable(refereeColumn, table, foreignKey);
	}

	private List<Info> all = Lists.newArrayList();

	/**
	 * @param foreignKey
	 * @param table
	 */
	private ChildTable(String table, String foreignKey)
	{
		this("id", table, foreignKey);
	}

	/**
	 * @param refereeColumn
	 * @param foreignKey
	 * @param table
	 */
	private ChildTable(String refereeColumn, String table, String foreignKey)
	{
		all.add(new Info(refereeColumn, table, foreignKey));
	}

	public List<Info> all()
	{
		return all;
	}

	/**
	 * @param foreignKey
	 * @param table
	 */
	public ChildTable and(String table, String foreignKey)
	{
		all.add(new Info(table, foreignKey));
		return this;
	}

	/**
	 * @param refereeColumn
	 * @param foreignKey
	 * @param table
	 */
	public ChildTable and(String refereeColumn, String table, String foreignKey)
	{
		all.add(new Info(refereeColumn, table, foreignKey));
		return this;
	}
}