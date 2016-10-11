/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.db;

/**
 * @Author alex
 * @CreateTime Aug 4, 2016 5:54:15 PM
 * @Version 1.0
 * @Since 1.0
 */
public interface Table
{
	// ensureAllValuesOf().between()

	Columns charTypedColumns();

	Column column(String column);

	Columns columns();

	Columns columns(String... columns);

	Columns columnsExcept(String... columns);

	/**
	 *
	 */
	void ensureSchemaRetrieved();

	Columns hasReferenceColumns();

	/**
	 * @return
	 */
	String name();


	/**
	 * @return primary key
	 */
	String primaryKey();

	/**
	 * set primary key
	 */
	Table primaryKey(String primaryKey);
}