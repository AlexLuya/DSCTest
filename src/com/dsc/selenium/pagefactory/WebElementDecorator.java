/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.pagefactory;

import static com.dsc.selenium.util.Util.wrap;

import java.lang.reflect.Field;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;

import com.dsc.selenium.Browser;
import com.dsc.selenium.ui.UIObject;

public class WebElementDecorator extends DefaultFieldDecorator
{
	private final Browser browser;

	// private CompositeFactory compositeFactory = new CompositeFactoryImpl();

	private UIObjectFactory uiObjectFactory = new UIObjectFactoryImpl();

	public WebElementDecorator(Browser browser)
	{
		super(new WebElementLocatorFactory(browser.getDriver()));
		this.browser = browser;
	}

	@Override
	public Object decorate(ClassLoader classLoader, Field field)
	{
		if (findByAnno(field) == null)
		{
			// for page elements without annotation or non page
			// elements,like:string
			return null;
		}

		WebElement wrapee = proxyForLocator(classLoader, factory.createLocator(field));

		// if (Composite.class.isAssignableFrom(field.getType()))
		// {
		// return decorateComposite(field, wrapee);
		// }

		if (UIObject.class.isAssignableFrom(field.getType()))
		{
			try
			{
				return decorateElement(field, wrapee);
			} catch (Exception e)
			{
				throw new RuntimeException(wrap("can't create element with--------id:--------" + annotatedId(field)), e);
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

	// private Object decorateComposite(final Field field, final WebElement
	// wrapee)
	// {
	// @SuppressWarnings("unchecked")
	// Composite composite = compositeFactory.create(browser, (Class<? extends
	// Composite>) field.getType(), wrapee);
	//
	// composite.setAnnotatedId(findByAnno(field).id());
	//
	// return composite;
	// }

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