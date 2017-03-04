/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util;

import static java.lang.String.format;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.dsc.test.common.ui.base.UIField;
import com.dsc.test.common.ui.base.Widget;
import com.dsc.util.common.Pair;
import com.dsc.util.common.model.IdName;
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

	public static String currentDateTime()
	{
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-ms", Locale.CHINA).format(new Date());
	}

	public static <T extends UIField<?>> void ensureItemsAvailable(List<T> items)
	{
		for (UIField<?> item : items)
		{
			item.ensureAvailable();
		}
	}

	public static String fileExt(String file)
	{
		if (file == null || !file.contains(".") || file.indexOf('.') == file.length() - 1)
		{
			return "";
		}

		return file.substring(file.lastIndexOf('.') + 1);
	}

	public static UIField<?> findChildByText(Widget<?> parent, String text)
	{
		for (UIField<?> child : parent.children())
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
			return format(body, args);
		}

		return body;
	}

	public static void mustBePositive(double value, String name) throws IllegalArgumentException
	{
		if (value < 0)
		{
			throw new IllegalArgumentException(name + " must >=0,but it is " + value);
		}
	}

	public static void mustNotNull(Object obj, String name) throws IllegalArgumentException
	{
		if (obj == null)
		{
			throw new IllegalArgumentException(name + " mustn't be null or empty");
		}
	}

	public static void mustNotNull(String name, Object[] array) throws IllegalArgumentException
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

	public static String[] params(String url)
	{
		// Pattern pattern = Pattern.compile("\\{(.*?)\\}");
		Matcher matcher = Pattern.compile("\\{\\w+\\}").matcher(url);

		List<String> finds = Lists.newArrayList();
		while (matcher.find())
		{
			finds.add(matcher.group(0));
		}

		if (finds.size() == 0)
		{
			return null;
		}

		return finds.toArray(new String[finds.size()]);
	}

	/**
	 * Selected option text.
	 *
	 * @param options
	 *            the options
	 * @param optionId
	 *            the option id
	 * @return the string
	 */
	public static String selectedOptionText(String[] options, int optionId)
	{
		return selectedOptionText(options, Integer.toString(optionId));
	}

	/**
	 * Selected option text.
	 *
	 * @param options
	 *            the options
	 * @param optionId
	 *            the option id
	 * @return the string
	 */
	public static String selectedOptionText(String[] options, String optionId)
	{
		return selectedOptionText(options, optionId, false);
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

	/**
	 * Selected option text.
	 *
	 * @param options
	 *            the options
	 * @param optionId
	 *            the option id
	 * @param optionIsTriple
	 *            the option is triple
	 * @return the string
	 */
	public static String selectedOptionText(String[] options, String optionId, boolean optionIsTriple)
	{
		for (int i = 0, len = options.length; i < len; i = i + (optionIsTriple ? 3 : 2))
		{
			if (optionId.equals(options[i]))
			{
				return options[i + 1];
			}
		}

		return null;
	}

	public static void sleep(double d)
	{
		try
		{
			Thread.sleep((long) (d * 1000));
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
		sleep(seconds);
	}

	public static String[] split(String str, String seprator)
	{
		return str.split(seprator);
	}

	public static String stringfy(Object obj)
	{
		return obj == null ? null : obj.toString();
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

	public static UIField<?> twoLevelsFindByText(Widget<?> parent, String text)
	{
		for (UIField<?> child : parent.children())
		{
			if (child.textIs(text) || findChildByText((Widget<?>) child, text) != null)
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

	public static String wrap(String format, Object... args)
	{
		mustNotNull(format, "format");

		return wrap(format(format, args));
	}
}