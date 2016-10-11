/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db;

import com.dsc.db.sql.Schema;

/**
 * @Author alex
 * @CreateTime Aug 8, 2016 11:40:24 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Column extends ColumnBase<Column>
{
	/**
	 * @param dataBase
	 * @param schema
	 */
	public Column(DataBase dataBase, Schema schema,Table	parent)
	{
		super(dataBase, schema, parent);
	}

	/**
	 * @return
	 */
	public boolean hasReferences()
	{
		return referencedColumn() != null;
	}

	public Column referencedColumn()
	{
		if (schema().getReferencedColumn() == null)
		{
			return null;
		}

		return new Column(dataBase, schema.column(schema().getReferencedColumn()),parent);
	}
}