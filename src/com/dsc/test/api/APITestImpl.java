/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dsc.test.api.base.ColumnCfg;
import com.dsc.test.api.base.Response;
import com.dsc.test.api.base.Test;
import com.google.common.collect.Lists;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

/**
 * @Author alex
 * @CreateTime 22.02.2017 09:33:30
 * @Version 1.0
 * @Since 1.0
 */
public class APITestImpl implements API
{
	private String					caseName;
	private String					domain;
	private RequestSpecification	given;
	private int						port	= 80;
	private List<Test>				tests	= Lists.newArrayList();

	@Override
	public API authorize(String token)
	{
		return this;
	}

	@Override
	public API authorize(String user, String password)
	{
		return this;
	}

	@Override
	public API delete(String data)
	{
		return this;
	}

	@Override
	public API domain(String domain)
	{
		this.domain = domain;
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#excel(java.lang.String)
	 */
	@Override
	public API excel(String file)
	{
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#excel(java.lang.String,
	 * com.dsc.test.api.base.ColumnCfg)
	 */
	@Override
	public API excel(String file, ColumnCfg column)
	{
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#excel(java.lang.String, java.lang.String,
	 * com.dsc.test.api.base.ColumnCfg)
	 */
	@Override
	public API excel(String file, String sheet, ColumnCfg column)
	{
		return this;
	}

	@Override
	public API got(String data)
	{
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#name(java.lang.String)
	 */
	@Override
	public API name(String name)
	{
		// NP- Auto-generated method stub
		return null;
	}

	@Override
	public API port(int port)
	{
		this.port = port;
		return this;
	}

	@Override
	public API post(String data)
	{
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#put(java.lang.String)
	 */
	@Override
	public API put(String data)
	{
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#resultAsCSV()
	 */
	@Override
	public String resultAsCSV()
	{


		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#returnJson()
	 */
	@Override
	public JSONObject returnJson()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#returnJsonArray()
	 */
	@Override
	public JSONArray returnJsonArray()
	{

		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#returnString()
	 */
	@Override
	public String returnString()
	{
		return exec(firstTest()).asString();
	}

	/**
	 * @param test
	 * @return
	 */
	private io.restassured.response.Response doExcel(Test test)
	{
		switch (test.method)
		{
			case DELETE:
				return given().delete(test.url);
			case GET:
				return given().get(test.url);
			case PATCH:
				return given().patch(test.url);
			case POST:
				return given().post(test.url);
			case PUT:
				return given().post(test.url);

		}

		return null;
	}

	private Response exec(Test test)
	{
		if (test.invalidField() != null)
		{
			return new Response("Ignored due to Must-have field: " + test.invalidField() + " is null or emtpy");
		}

		return new Response(doExcel(test));
	}

	/**
	 * @return
	 */
	private Test firstTest()
	{
		return test(0);
	}

	/**
	 * @return
	 */
	private RequestSpecification given()
	{
		if (given == null)
		{
			given = RestAssured.given();
		}
		return given;
	}

	/**
	 * @param index
	 * @return
	 */
	private Test test(int index)
	{
		return tests.get(index);
	}

}
