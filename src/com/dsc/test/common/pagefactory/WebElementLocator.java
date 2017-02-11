/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.pagefactory;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

/**
 * @Author alex
 * @CreateTime Dec 24, 2014 6:49:10 PM
 * @Version 1.0
 * @Since 1.0
 */
public class WebElementLocator implements ElementLocator
{
	private final By			by;
	private WebElement			cachedElement;
	private List<WebElement>	cachedElementList;
	private final SearchContext	searchContext;
	private final boolean		shouldCache;

	/**
	 * Creates a new element locator.
	 *
	 * @param searchContext
	 *            The context to use when finding the element
	 * @param field
	 *            The field on the Page Object that will hold the located value
	 */
	public WebElementLocator(SearchContext searchContext, Field field)
	{
		this.searchContext = searchContext;
		Annotations annotations = new Annotations(field);
		shouldCache = annotations.isLookupCached();
		by = annotations.buildBy();
	}

	/**
	 * Find the element.
	 */
	@Override
	public WebElement findElement()
	{
		if (cachedElement != null && shouldCache)
		{
			return cachedElement;
		}

		WebElement element = searchContext.findElement(by);
		if (shouldCache)
		{
			cachedElement = element;
		}

		return element;
	}

	/**
	 * Find the element list.
	 */
	@Override
	public List<WebElement> findElements()
	{
		if (cachedElementList != null && shouldCache)
		{
			return cachedElementList;
		}

		List<WebElement> elements = searchContext.findElements(by);
		if (shouldCache)
		{
			cachedElementList = elements;
		}

		return elements;
	}
}
