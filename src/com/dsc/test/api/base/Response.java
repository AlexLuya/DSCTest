/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api.base;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dsc.util.StringUtil;
import com.google.common.base.Strings;

/**
 * @Author alex
 * @CreateTime 02.03.2017 13:58:49
 * @Version 1.0
 * @Since 1.0
 */
public class Response
{
	private String								message;
	private io.restassured.response.Response	raw;

	/**
	 * @param raw
	 */
	public Response(io.restassured.response.Response raw)
	{
		this.raw = raw;
	}

	public Response(String message)
	{
		this.message = message;
	}

	public JSONObject asJson()
	{
		if (failed())
		{
			return null;
		}

		return new JSONObject(asString());
	}

	public JSONArray asJsonArray()
	{
		if (failed())
		{
			return null;
		}

		return new JSONArray(asString());
	}

	/**
	 * @return
	 */
	public String asString()
	{
		return message != null ? message : StringUtil.stripLeadingAndTailWhitespace(raw.asString());
	}

	/**
	 * @return
	 */
	public long getTime()
	{
		return raw.getTime();
	}

	/**
	 *
	 */
	private boolean failed()
	{
		if (message != null)
		{
			return true;
		}

		if (raw.getStatusCode() != 200)
		{
			return true;
		}

		if (Strings.isNullOrEmpty(asString()))
		{
			return true;
		}

		return false;
	}
}
