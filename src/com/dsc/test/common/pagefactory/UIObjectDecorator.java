/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.pagefactory;

import static com.dsc.util.Util.wrap;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.UIObject;

public abstract class UIObjectDecorator extends DefaultFieldDecorator
{
	private final Context<?, ?>	context;

	// private CompositeFactory compositeFactory = new CompositeFactoryImpl();

	private UIObjectFactory		uiObjectFactory	= new UIObjectFactory();

	public UIObjectDecorator(Context<?, ?> context, SearchContext searchContext)
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

	/**
	 * @param field
	 * @return
	 */
	protected abstract String annotatedId(Field field);

	/**
	 * @param field
	 * @return
	 */
	protected <T> T findByAnno(Field field)
	{
		return field.getAnnotation(findByClass());
	}

	/**
	 * @return
	 */
	protected abstract <T> Class<T> findByClass();

	private Object decorateElement(final Field field, final WebElement wrapee) throws Exception
	{
		@SuppressWarnings("unchecked")
		UIObject element = uiObjectFactory.create(context, (Class<? extends UIObject>) field.getType(), wrapee);

		element.setAnnotatedId(annotatedId(field));

		return element;
	}
}