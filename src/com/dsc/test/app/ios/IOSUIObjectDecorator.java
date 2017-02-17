/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ios;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;

import com.dsc.test.common.Context;
import com.dsc.test.common.pagefactory.UIObjectDecorator;

import io.appium.java_client.pagefactory.iOSFindBy;

/**
 * @Author alex
 * @CreateTime 17.02.2017 17:48:09
 * @Version 1.0
 * @Since 1.0
 */
public class IOSUIObjectDecorator extends UIObjectDecorator
{

	/**
	 * @param context
	 * @param searchContext
	 */
	public IOSUIObjectDecorator(Context<?, ?> context, SearchContext searchContext)
	{
		super(context, searchContext);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.dsc.test.common.pagefactory.UIObjectDecorator#annotatedId(java.lang.
	 * reflect.Field)
	 */
	@Override
	protected String annotatedId(Field field)
	{
		return ((iOSFindBy) findByAnno(field)).id();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.pagefactory.UIObjectDecorator#findByClass()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> Class<T> findByClass()
	{
		return (Class<T>) iOSFindBy.class;
	}
}