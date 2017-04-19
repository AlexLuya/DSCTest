/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.db;

import java.sql.ResultSet;
import java.util.List;

import com.dsc.test.db.sql.ChildTable;

/**
 * The Interface Table.
 *
 * @Author alex
 * @CreateTime Aug 4, 2016 5:54:15 PM
 * @Version 1.0
 * @Since 1.0
 */
public interface Table
{

	/**
	 * Char typed columns.
	 *
	 * @return the columns
	 */
	Columns charTypedColumns();

	/**
	 * Column.
	 *
	 * @param column
	 *            the column
	 * @return the column
	 */
	Column column(String column);

	/**
	 * Columns.
	 *
	 * @return the columns
	 */
	Columns columns();

	/**
	 * Columns.
	 *
	 * @param columns
	 *            the columns
	 * @return the columns
	 */
	Columns columns(String... columns);

	/**
	 * Columns except.
	 *
	 * @param columns
	 *            the columns
	 * @return the columns
	 */
	Columns columnsExcept(String... columns);

	/**
	 * Count.
	 *
	 * @return the int
	 */
	int count();

	/**
	 * Delete all.
	 *
	 * @return the int
	 */
	int deleteAll();

	/**
	 * Delete by.
	 *
	 * @param sql
	 *            the sql
	 * @return the count of deleted row
	 */
	int deleteBy(String sql);

	/**
	 * Delete by.
	 *
	 * @param column
	 *            the column
	 * @param cellValue
	 *            the cell value
	 * @return the count of deleted row
	 */
	int deleteBy(String column, Object cellValue);

	/**
	 * Delete by id.
	 *
	 * @param id
	 *            the id
	 * @return the count of deleted row
	 */
	int deleteById(Object id);

	/**
	 * Delete cascadely.
	 *
	 * @param column
	 *            the column for filtering
	 * @param cellValue
	 *            the cell value for filtering
	 * @param childTablesInfo
	 *            the infos where contains how child tables reference to parent
	 *            table
	 * @return the count of deleted row
	 */
	int deleteCascadely(String column, Object cellValue, List<ChildTable.Info> childTablesInfo);

	/**
	 * Ensure schema retrieved.
	 */
	void ensureSchemaRetrieved();

	/**
	 * Existed by.
	 *
	 * @param query
	 *            the query
	 * @return true, if successful
	 */
	boolean existedBy(String query);

	/**
	 * Existed by.
	 *
	 * @param column
	 *            the column
	 * @param cellValue
	 *            the cell value
	 * @return true, if successful
	 */
	boolean existedBy(String column, Object cellValue);

	/**
	 * Existed by id.
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	boolean existedById(Object id);

	/**
	 * Checks for reference columns.
	 *
	 * @return the columns
	 */
	Columns hasReferenceColumns();

	/**
	 * Insert.
	 *
	 * @param sql
	 *            the sql
	 * @param values
	 *            the values
	 */
	void insert(String sql, Object[][] values);

	/**
	 * Name.
	 *
	 * @return the string
	 */
	String name();

	/**
	 * Nullify multiple cells.
	 *
	 * @param id
	 *            the id
	 * @param columns
	 *            the columns
	 */
	int nullifyCell(Object id, String... columns);

	/**
	 * Nullify cell.<br>
	 * <ul>
	 * <li>if column is nullable,cell will be set to null;</li>
	 * <li>if column is NOT NULL,and <code>defaultValue</code> provided,cell will be set to provided value</li>
	 * <li>if column is NOT NULL,and <code>defaultValue</code> NOT provided,cell will be set to default value in DDL</li>
	 * </ul>
	 *
	 * @param id
	 *            the id
	 * @param column
	 *            the column
	 * @param defaultValue
	 *            the default value if column MUST not null in DDL,</br>
	 *            can be ignored if column is nullable or DDL has default value like: NOT NULL DEFAULT xxxx
	 * @return the int
	 *
	 * @exception RunTimeException to indicate integrity constraint violation</br>
	 * if column is NOT NULL,no default value in DDL,and <code>defaultValue</code> NOT provided or null
	 */
	int nullifyCell(Object id, String column, Object... defaultValue);

	/**
	 * Primary key.
	 *
	 * @return primary key
	 */
	String primaryKey();

	/**
	 * set primary key.
	 *
	 * @param primaryKey
	 *            the primary key
	 * @return the table
	 */
	Table primaryKey(String primaryKey);

	/**
	 * Select by.
	 *
	 * @param sql
	 *            the sql
	 * @return the result set
	 */
	ResultSet selectBy(String sql);

	/**
	 * Select by.
	 *
	 * @param column
	 *            the column
	 * @param cellValue
	 *            the cell value
	 * @return the result set
	 */
	ResultSet selectBy(String column, Object cellValue);

	/**
	 * Select a column by another column and it's value.
	 *
	 * @param filterColumn
	 *            the column to be used as filtering condition
	 * @param filterValue
	 *            the filter value
	 * @param resultColumn
	 *            the result column
	 * @return the unique cell value if only one row retrieved,the list of cells
	 *         value if multiple rows returned,null if nothing got selected out
	 */
	Object selectBy(String filterColumn, Object filterValue, String resultColumn);

	/**
	 * Select by id.
	 *
	 * @param id
	 *            the id
	 * @return the result set
	 */
	ResultSet selectById(Object id);

	/**
	 * Select by id and column.
	 *
	 * @param id
	 *            the id
	 *
	 * @param column
	 *            the column
	 * @return the result cell value,,null if nothing got selected out
	 */
	Object selectById(Object id, String column);
}