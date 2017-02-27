/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.pagefactory;

import static com.dsc.util.Util.wrap;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.dsc.test.app.App;
import com.dsc.test.app.pagefactory.appium.AppiumFieldDecorator;
import com.dsc.test.common.UIFieldFactory;
import com.dsc.test.common.ui.base.GeneralUIField;
import com.dsc.test.common.ui.base.UIField;

/**
 * @Author alex
 * @CreateTime 23.02.2017 15:15:31
 * @Version 1.0
 * @Since 1.0
 */
public abstract class AppFiledDecorator extends AppiumFieldDecorator
{
	private final App<?, ?>	app;

	// private CompositeFactory compositeFactory = new CompositeFactoryImpl();

	private UIFieldFactory	uiObjectFactory	= new UIFieldFactory();

	/**
	 * @param context
	 */
	public AppFiledDecorator(App<?, ?> app, SearchContext searchContext)
	{
		super(searchContext);
		this.app = app;
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

		WebElement wrapee = proxyForAnElement(widgetLocatorFactory.createLocator(field));

		if (UIField.class.isAssignableFrom(field.getType()))
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
		UIField<?> element = uiObjectFactory.create(app, (Class<? extends GeneralUIField>) field.getType(), wrapee);

		element.setAnnotatedId(annotatedId(field));

		return element;
	}
}
