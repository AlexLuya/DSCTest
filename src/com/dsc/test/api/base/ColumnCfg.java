/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api.base;

/**
 * @Author alex
 * @CreateTime 02.03.2017 13:37:46
 * @Version 1.0
 * @Since 1.0
 */
public class ColumnCfg
{
	public static ColumnCfg defaultCfg()
	{
		return new ColumnCfg();
	}

	public static ColumnCfg name(int col)
	{
		return new ColumnCfg(col);
	}

	public int	caseName	= 1;// generally,0 column is module

	public int	data		= 4;
	public int	expectation	= 5;
	public int	method		= 3;
	// public int result = 6;
	public int	url			= 2;

	public ColumnCfg()
	{
	}

	/**
	 * @param name
	 */
	public ColumnCfg(int name)
	{
		this.caseName = name;
	}

	public ColumnCfg data(int data)
	{
		this.data = data;
		return this;
	}

	public ColumnCfg expectation(int expectation)
	{
		this.expectation = expectation;
		return this;
	}

	public ColumnCfg method(int method)
	{
		this.method = method;
		return this;
	}

	public ColumnCfg url(int url)
	{
		this.url = url;
		return this;
	}
}
