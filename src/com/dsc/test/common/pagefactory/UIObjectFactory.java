/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.pagefactory;

import static com.dsc.util.Util.wrap;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.UIObject;

public class UIObjectFactory
{
	public <T extends UIObject> T create(Context context, Class<T> wrapping, WebElement wrapee) throws Exception
	{
		// create instance by reflection
		T instance = null;
		try
		{
			instance = wrapping.getDeclaredConstructor(context.getClass(), WebElement.class).newInstance(context, wrapee);
		} catch (NoSuchMethodException e)
		{
			throw new RuntimeException(msgOfNoSuchConstructor(wrapping), e.getCause());
		} catch (SecurityException e)
		{
			throw new RuntimeException(msgOfCanNotInvokeConstructor(wrapping), e.getCause());
		} catch (InstantiationException e)
		{
			throw new RuntimeException(msgOfCanNotInstantiation(wrapping), e.getCause());
		}

		// ensure element presented in the page
		//		ensurePresented(instance);

		return instance;
		// return
		// findImplementingClass(wrapping).getDeclaredConstructor(browser.getClass(),
		// WebElement.class).newInstance(context(),
		// wrapee);
	}

	//	private void ensurePresented(Object obj)
	//	{
	//		Method method = null;
	//		try
	//		{
	//			method = obj.getClass().getMethod("ensurePresented");
	//		} catch (SecurityException e)
	//		{
	//			// exception handling omitted for brevity
	//		} catch (NoSuchMethodException e)
	//		{
	//			// exception handling omitted for brevity
	//		}
	//
	//		try
	//		{
	//			method.invoke(obj);
	//		} catch (Exception e)
	//		{
	//		}
	//	}

	private String msgOfCanNotInstantiation(Class<?> clz)
	{
		return String.format(
				"--------%s--------can't be instantiated\n"
						+ wrap("Possible reasons:\n")
						+ "① it is an abstract class, an interface, an array class, a primitive type, or void\n"
						+ "② it has no nullary constructor\n",
						clz.getSimpleName());
	}

	private String msgOfCanNotInvokeConstructor(Class<?> clz)
	{
		return String.format(
				"Constructor--------%s(Context,WebElement)--------can't be invoked\n"
						+ wrap("Possible reasons:\n")
						+ "① it is private\n",
						clz.getSimpleName());
	}

	private String msgOfNoSuchConstructor(Class<?> clz)
	{
		return String.format(
				"expected constructor--------%s(Context,WebElement)--------not existed\n"
						+ wrap("Possible reasons:\n")
						+ "① No constructor existed\n"
						+ "② Parameter count mismatched----only: %s(Context) or %s(WebElement) exist\n"
						+ "③ Parameters order mismatched---expected (Context,WebElement) but got (WebElement,Context)\n",
						clz.getSimpleName(), clz.getSimpleName(), clz.getSimpleName(), clz.getSimpleName());
	}

	// @SuppressWarnings("unchecked")
	// private <E extends UIObject> Class<? extends E>
	// findImplementingClass(final Class<E> elementClass)
	// {
	// try
	// {
	// return (Class<? extends E>) Class.forName(elementClass.getName());
	// } catch (ClassNotFoundException e)
	// {
	// throw new RuntimeException("Unable to load class for " +
	// elementClass.getName(), e);
	// }
	// }
}
