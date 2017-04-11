/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common;

import static com.dsc.util.Util.wrap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.ui.base.UIField;

public class UIFieldFactory
{
	public <T extends UIField<?>> T create(Context<?, ?> context, Class<T> wrapping, WebElement wrapee) //throws Exception
	{
		// create instance by reflection
		try
		{
			return findConstructor(context.getClass(), wrapping).newInstance(context, wrapee);
		} catch (NoSuchMethodException e)
		{
			throw new RuntimeException(msgOfNoSuchConstructor(wrapping), e.getCause());
		} catch (SecurityException e)
		{
			throw new RuntimeException(msgOfCanNotInvokeConstructor(wrapping), e.getCause());
		} catch (InstantiationException e)
		{
			throw new RuntimeException(msgOfCanNotInstantiation(wrapping), e.getCause());
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param context
	 * @param wrapping
	 * @return
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	private <T extends UIField<?>> Constructor<T> findConstructor(Class<?> context, Class<T> wrapping) throws NoSuchMethodException
	{

		Constructor<?>[] constructors = wrapping.getConstructors();

		for (Constructor<?> c : constructors)
		{
			Parameter[] params = c.getParameters();
			// HP clean this mess
			if (params.length == 2 && params[0].getType().isAssignableFrom(context)
					&& WebElement.class.isAssignableFrom(params[1].getType()))
			{
				return (Constructor<T>) c;
			}
		}

		return null;
	}

	private String msgOfCanNotInstantiation(Class<?> clz)
	{
		return String.format("--------%s--------can't be instantiated\n" + wrap("Possible reasons:\n")
		+ "① it is an abstract class, an interface, an array class, a primitive type, or void\n"
		+ "② it has no nullary constructor\n", clz.getSimpleName());
	}

	private String msgOfCanNotInvokeConstructor(Class<?> clz)
	{
		return String.format("Constructor--------%s(Context,WebElement)--------can't be invoked\n" + wrap("Possible reasons:\n")
		+ "① it is private\n", clz.getSimpleName());
	}

	private String msgOfNoSuchConstructor(Class<?> clz)
	{
		return String.format(
				"expected constructor--------%s(Context,WebElement)--------not existed\n" + wrap("Possible reasons:\n")
				+ "① No constructor existed\n"
				+ "② Parameter count mismatched----only: %s(Context) or %s(WebElement) exist\n"
				+ "③ Parameters order mismatched---expected (Context,WebElement) but got (WebElement,Context)\n",
				clz.getSimpleName(), clz.getSimpleName(), clz.getSimpleName(), clz.getSimpleName());
	}
}
