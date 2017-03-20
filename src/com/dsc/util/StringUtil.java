/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util;

import static java.lang.String.format;

/**
 * @Author alex
 * @CreateTime 08.03.2017 16:02:08
 * @Version 1.0
 * @Since 1.0
 */
public class StringUtil
{
	public static String stringfy(Object obj)
	{
		return obj == null ? null : toUTF8(obj.toString());
	}

	public static String stripLeadingAndTailWhitespace(String str)
	{
		if (str == null)
		{
			return null;
		}

		return str.replaceAll("^\\s+|\\s+$", "");
	}

	public static String toUTF8(Object str)
	{
		try
		{
			//			Log.debug(format("'%s' will be converted to UTF8", str));
			return new String(str.toString().getBytes("UTF-8"));
		} catch (Exception e)
		{
			Log.debug(format("'%s' can't be converted to UTF8", str));
			return null;
		}
	}

	// remove formatting characters
	public static String unformat(final String original)
	{
		if (original == null)
		{
			return null;
		}

		StringBuffer escapedBuffer = new StringBuffer();

		for (int i = 0; i < original.length(); i++)
		{
			if (original.charAt(i) != '\n' && original.charAt(i) != '\r' && original.charAt(i) != '\t')
			{
				escapedBuffer.append(original.charAt(i));
				continue;
			}
			Log.debug(format("'%s''s newline, carriage return and tab removed", original));
		}

		return escapedBuffer.toString();
	}
}
