/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.pagefactory;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * @Author alex
 * @CreateTime Dec 24, 2014 6:47:31 PM
 * @Version 1.0
 * @Since 1.0
 */
public class WebElementLocatorFactory implements ElementLocatorFactory
{
	private final SearchContext searchContext;

	public WebElementLocatorFactory(SearchContext searchContext)
	{
		this.searchContext = searchContext;
	}

	@Override
	public ElementLocator createLocator(Field field)
	{
		return new WebElementLocatorImpl(searchContext, field);
	}
}