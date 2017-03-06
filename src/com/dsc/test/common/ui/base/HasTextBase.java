/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

/**
 * @Author alex
 * @CreateTime 06.03.2017 11:49:05
 * @Version 1.0
 * @Since 1.0
 */
public class HasTextBase extends GeneralUIField implements HasText
{

	/**
	 * @param context
	 * @param id
	 */
	public HasTextBase(Context<?, ? extends WebDriver> context, String id)
	{
		super(context, id);
	}

	/**
	 * @param context
	 * @param wrappee
	 */
	public HasTextBase(Context<?, ? extends WebDriver> context, WebElement wrappee)
	{
		super(context, wrappee);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.HasText#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String text)
	{
		return super.contains(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.HasText#endsWith(java.lang.String)
	 */
	@Override
	public boolean endsWith(String text)
	{
		return super.endsWith(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.HasText#partOf(java.lang.String)
	 */
	@Override
	public boolean partOf(String text)
	{
		return super.partOf(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.HasText#startsWith(java.lang.String)
	 */
	@Override
	public boolean startsWith(String text)
	{
		return super.startsWith(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.HasText#text()
	 */
	@Override
	public String text()
	{
		return super.text();
	}
}
