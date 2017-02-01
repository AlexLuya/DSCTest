/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.pagefactory;

import static com.dsc.test.web.util.Util.wrap;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;

import com.dsc.test.web.Browser;
import com.dsc.test.web.ui.UIObject;

public class WebElementDecorator extends DefaultFieldDecorator
{
	private final Browser browser;

	// private CompositeFactory compositeFactory = new CompositeFactoryImpl();

	private UIObjectFactory uiObjectFactory = new UIObjectFactoryImpl();

	public WebElementDecorator(Browser browser,SearchContext searchContext)
	{
		super(new WebElementLocatorFactory(searchContext));
		this.browser = browser;
	}

	@Override
	public Object decorate(ClassLoader classLoader, Field field)
	{
		if (findByAnno(field) == null)
		{
			// ignore field without FindBy annotation or non page
			// elements like:string
			return null;
		}

		WebElement wrapee = proxyForLocator(classLoader, factory.createLocator(field));

		if (UIObject.class.isAssignableFrom(field.getType()))
		{
			try
			{
				return decorateElement(field, wrapee);
			} catch (Exception e)
			{
				throw new RuntimeException(wrap("can't create element with--------id--------" + annotatedId(field)), e);
			}
		}

		return super.decorate(classLoader, field);
	}

	/**
	 * @param itself
	 * @return
	 */
	protected String annotatedId(Field itself)
	{
		return findByAnno(itself).id();
	}

	private Object decorateElement(final Field field, final WebElement wrapee) throws Exception
	{
		@SuppressWarnings("unchecked")
		UIObject element = uiObjectFactory.create(browser, (Class<? extends UIObject>) field.getType(), wrapee);

		element.setAnnotatedId(findByAnno(field).id());

		return element;
	}

	/**
	 * @param field
	 * @return
	 */
	private FindBy findByAnno(Field field)
	{
		return field.getAnnotation(FindBy.class);
	}
}