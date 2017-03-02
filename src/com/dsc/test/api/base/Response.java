/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api.base;

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

	/**
	 * @return
	 */
	public String asString()
	{
		return message != null ? message : raw.asString();
	}

}
