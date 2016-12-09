/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.util;

import static java.lang.String.format;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dsc.selenium.ui.UIObject;
import com.dsc.test.base.Pair;
import com.dsc.test.model.IdName;
import com.dsc.util.Log;
import com.google.common.collect.Lists;

// import com.dsc.athena.test.Statics;

/**
 * The Class Util.
 *
 * @Author alex
 * @CreateTime Aug 26, 2014 7:56:44 PM
 * @Version 1.0
 * @Since v1.0
 */
public class Util
{
	public static String capitalizeFirstLetter(String str)
	{
		if (str == null || str.isEmpty())
		{
			throw new IllegalArgumentException("mustn't be null or empty");
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static boolean contains(String item, String... set)
	{
		for (String elem : set)
		{
			if (item.equals(elem))
			{
				return true;
			}
		}

		return false;
	}

	public static String fileExt(String file)
	{
		if (file == null || !file.contains(".") || file.indexOf('.') == file.length() - 1)
		{
			return "";
		}

		return file.substring(file.lastIndexOf('.') + 1);
	}

	public static UIObject findChildByText(UIObject parent, String text)
	{
		for (UIObject child : parent.children())
		{
			if (child.textIs(text))
			{
				return child;
			}
		}

		return null;
	}

	public static List<String> ids(List<? extends IdName> list)
	{
		List<String> ids = Lists.newArrayList();

		for (IdName item : list)
		{
			ids.add(item.getId());
		}

		return ids;
	}

	public static boolean isNotEqual(String oldContent, String notNullNewContent)
	{
		return !notNullNewContent.equals(oldContent);
	}

	public static <T extends IdName> T itemById(List<T> items, String id)
	{
		for (T item : items)
		{
			if (id.equals(item.getId()))
			{
				return item;
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T extends IdName> String joinedNames(List<T> list)
	{
		return StringUtils.join(names(list));
	}

	public static String message(String body, Object... args)
	{
		mustNotNullOrEmpty(body, "message body");

		if (null != args && args.length > 0)
		{
			return  format(body, args);
		}

		return body;
	}

	public static void mustNotNull(Object obj, String name) throws IllegalArgumentException
	{
		if (obj == null)
		{
			throw new IllegalArgumentException(name + " mustn't be null or empty");
		}
	}

	public static void mustNotNull(String name,Object[] array) throws IllegalArgumentException
	{
		mustNotNull(array, name);

		if (array.length == 0)
		{
			throw new IllegalArgumentException(name + " mustn't be empty");
		}

		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == null)
			{
				throw new IllegalArgumentException(String.format("The %i th item of %s is null", i, name));
			}
		}
	}

	public static void mustNotNullOrEmpty(String value, String name) throws IllegalArgumentException
	{
		if (nullOrEmpty(value))
		{
			throw new IllegalArgumentException(name + " mustn't be null or empty");
		}
	}

	public static <T extends IdName> List<String> names(List<T> list)
	{
		List<String> names = Lists.newArrayList();

		for (IdName item : list)
		{
			names.add(item.getName());
		}

		return names;
	}

	/**
	 * @param attrs
	 * @param attrName
	 * @return
	 */
	public static boolean notContain(String[] attrs, String attrName)
	{
		for (String n : attrs)
		{
			if (n.equals(attrName))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean notContains(String[] all, String elem)
	{
		for (String e : all)
		{
			if (e.equals(elem))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean notNullOrEmpty(String str)
	{
		return !nullOrEmpty(str);
	}

	public static String nullIfEmpty(String str)
	{
		if ("".equals(str))
		{
			return null;
		}

		return str;
	}

	public static boolean nullOrEmpty(String str)
	{
		return str == null || str.trim().isEmpty();
	}

	// public static <T> T annoByName(Field field,String name){
	// Annotation[] annotations = field.getAnnotations();
	//
	// for(Annotation anno : annotations){
	// if(anno.getClass().getSimpleName().equals(name)){
	// return (T) anno;
	// }
	// }
	//
	// return null;
	// }

	public static void sleep(int millisecond)
	{
		try
		{
			Thread.sleep(millisecond);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param seconds
	 */
	public static void sleep(int seconds, String elementId)
	{
		Log.info("Waiting %d seconds for ******'%s'****** to be displayed fully", seconds, elementId);
		sleep(seconds * 1000);
	}

	public static String[] split(String str, String seprator)
	{
		return str.split(seprator);
	}

	/**
	 * @param swap
	 * @return
	 */
	public static <T extends Pair<?>> List<T> swap(List<T> diffs)
	{
		for (T cp : diffs)
		{
			cp.swap();
		}

		return diffs;
	}

	public static String toFixLength(String string, int maxLength)
	{
		if (string.length() > maxLength)
		{
			return string.substring(0, maxLength) + "...";
		}

		return string;
	}

	public static void tryToThrowOutIllegalStateException(Object value, String description) throws IllegalStateException
	{
		if (value == null)
		{
			throw new IllegalStateException(description);
		}
	}

	public static UIObject twoLevelsFindByText(UIObject parent, String text)
	{
		for (UIObject child : parent.children())
		{
			if (child.textIs(text) || findChildByText(child, text) != null)
			{
				// CAUTION:even second case,return child not child's child
				return child;
			}
		}

		return null;
	}

	public static String wrap(String wrapee)
	{
		return "★★★   " + wrapee;
	}
}