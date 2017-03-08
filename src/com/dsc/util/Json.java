/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * @Author alex
 * @CreateTime 06.03.2017 14:30:19
 * @Version 1.0
 * @Since 1.0
 */
public class Json
{
	private static final Gson gson = new Gson();

	public static String formatIfItIs(Object obj)
	{
		if (obj == null)
		{
			return null;
		}

		String str = StringUtil.toUTF8(obj.toString());

		if (not(str))
		{
			return str;
		}

		JsonElement el = new JsonParser().parse(str);

		return new GsonBuilder().setPrettyPrinting().create().toJson(el);
	}

	public static boolean not(String str)
	{
		try
		{
			gson.fromJson(str, Object.class);
			return false;
		} catch (JsonSyntaxException ex)
		{
			return true;
		}
	}
}