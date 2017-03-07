/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api.base;

/**
 * The Class ColumnCfg.
 *
 * @Author alex
 * @CreateTime 02.03.2017 13:37:46
 * @Version 1.0
 * @Since 1.0
 */
public class ColumnCfg
{

	/**
	 * @param idx
	 * @return
	 */
	public static boolean columnNofExisted(int column)
	{
		if (column < 0)
		{
			return true;
		}

		return false;
	}

	/**
	 * Default cfg.
	 *
	 * @return the column cfg
	 */
	public static ColumnCfg defaultCfg()
	{
		return new ColumnCfg();
	}

	/**
	 * create a ColumnCfg with name column specified.
	 *
	 * @param nameColumn
	 *            the name column,or negative if it isn't existd
	 * @return the column cfg
	 */
	public static ColumnCfg name(int nameColumn)
	{
		return new ColumnCfg(nameColumn);
	}

	/** The case name. */
	public int	caseName	= 1;// generally,0 column is module

	/** The data. */
	public int	data		= 4;

	/** The expectation. */
	public int	expectation	= 5;

	/** The method. */
	public int	method		= 3;

	/** The url. */
	// public int result = 6;
	public int	url			= 2;

	/**
	 * Instantiates a new column cfg.
	 */
	public ColumnCfg()
	{
	}

	/**
	 * Instantiates a new column cfg.
	 *
	 * @param name
	 *            the name
	 */
	public ColumnCfg(int name)
	{
		this.caseName = name;
	}

	/**
	 * Data.
	 *
	 * @param data
	 *            the data
	 * @return the column cfg
	 */
	public ColumnCfg data(int data)
	{
		this.data = data;
		return this;
	}

	/**
	 * Expectation.
	 *
	 * @param expectation
	 *            the expectation
	 * @return the column cfg
	 */
	public ColumnCfg expectation(int expectation)
	{
		this.expectation = expectation;
		return this;
	}

	/**
	 * Method.
	 *
	 * @param method
	 *            the method
	 * @return the column cfg
	 */
	public ColumnCfg method(int method)
	{
		this.method = method;
		return this;
	}

	/**
	 * Url.
	 *
	 * @param url
	 *            the url
	 * @return the column cfg
	 */
	public ColumnCfg url(int url)
	{
		this.url = url;
		return this;
	}
}
