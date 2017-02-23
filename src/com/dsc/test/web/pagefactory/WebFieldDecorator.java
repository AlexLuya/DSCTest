/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.pagefactory;

import static com.dsc.util.Util.wrap;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;

import com.dsc.test.common.UIFieldFactory;
import com.dsc.test.common.ui.base.UIObject;
import com.dsc.test.web.Browser;

public class WebFieldDecorator extends DefaultFieldDecorator
{
	private final Browser	context;

	// private CompositeFactory compositeFactory = new CompositeFactoryImpl();

	private UIFieldFactory	uiObjectFactory	= new UIFieldFactory();

	public WebFieldDecorator(Browser context, SearchContext searchContext)
	{
		super(new WebElementLocatorFactory(searchContext));
		this.context = context;
	}

	@Override
	public Object decorate(ClassLoader classLoader, Field field)
	{
		if (field.getAnnotations().length == 0)
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
				throw new RuntimeException(wrap("can't create element with id='" + annotatedId(field) + "'"), e);
			}
		}

		return super.decorate(classLoader, field);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.dsc.test.common.pagefactory.UIFieldDecorator#annotatedId(java.lang.
	 * reflect.Field)
	 */
	protected String annotatedId(Field field)
	{
		return ((FindBy) findByAnno(field)).id();
	}

	protected <T> T findByAnno(Field field)
	{
		return field.getAnnotation(findByClass());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.pagefactory.UIFieldDecorator#findByClass()
	 */
	@SuppressWarnings("unchecked")
	protected <T> Class<T> findByClass()
	{
		return (Class<T>) FindBy.class;
	}

	private Object decorateElement(final Field field, final WebElement wrapee) throws Exception
	{
		@SuppressWarnings("unchecked")
		UIObject element = uiObjectFactory.create(context, (Class<? extends UIObject>) field.getType(), wrapee);

		element.setAnnotatedId(annotatedId(field));

		return element;
	}
}