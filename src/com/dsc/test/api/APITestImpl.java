/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api;

import static com.dsc.util.Util.currentDateTime;
import static com.dsc.util.Util.mustBePositive;
import static com.dsc.util.Util.mustNotNull;
import static com.dsc.util.Util.mustNotNullOrEmpty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import com.dsc.test.api.base.ColumnCfg;
import com.dsc.test.api.base.HttpMethod;
import com.dsc.test.api.base.Response;
import com.dsc.test.api.base.Test;
import com.dsc.test.common.report.Report;
import com.dsc.test.common.report.Summary;
import com.dsc.util.Log;
import com.google.common.collect.Lists;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.testworx.spock.exceldatareader.client.ExcelDataReader;

/**
 * @Author alex
 * @CreateTime 22.02.2017 09:33:30
 * @Version 1.0
 * @Since 1.0
 */
public class APITestImpl implements API
{
	private static final int		DEFAULT_IGNORED_ROWS	= 1;
	// first sheet will be selected if sheet name is ""
	private static final String		FIRST_SHEET				= "";
	private String					caseName;
	private String					domain;
	private RequestSpecification	given;
	private int						port					= 80;
	private List<Test>				tests					= Lists.newArrayList();
	private String					url;

	@Override
	public API basicAuth(String user, String password)
	{
		mustNotNullOrEmpty(user, "user");
		mustNotNullOrEmpty(password, "password");

		given().auth().basic(user, password);

		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#cookie(java.lang.String, java.lang.String)
	 */
	@Override
	public API cookie(String name, Object value)
	{
		mustNotNullOrEmpty(name, "cookie name");
		mustNotNull(value, "cookie value");

		given.cookie(name, value);

		return this;
	}

	@Override
	public API delete(String data)
	{
		setSingleTest(HttpMethod.DELETE, data);

		return this;
	}

	@Override
	public API domain(String domain)
	{
		mustNotNullOrEmpty(this.domain = domain, "domain");

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
		return excel(file, ColumnCfg.defaultCfg());
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
		return excel(file, DEFAULT_IGNORED_ROWS, column);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#excel(java.lang.String, int,
	 * com.dsc.test.api.base.ColumnCfg)
	 */
	@Override
	public API excel(String file, int ignoredRows, ColumnCfg column)
	{
		return excel(file, ignoredRows, FIRST_SHEET, column);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#excel(java.lang.String, int, java.lang.String,
	 * com.dsc.test.api.base.ColumnCfg)
	 */
	@Override
	public API excel(String file, int ignoredRows, String sheet, ColumnCfg column)
	{
		mustNotNullOrEmpty(file, "excel file path");
		mustBePositive(ignoredRows, "ignored rows");
		mustNotNull(sheet, "sheet");
		mustNotNull(column, "excel column config");

		Path path = Paths.get(file);
		ExcelDataReader excel = new ExcelDataReader(path.getParent().toString(), path.getFileName().toString());

		for (int i = ignoredRows; excel.getDataFromRow(i) != null; i++)
		{
			tests.add(new Test(excel.getDataFromRow(i), column, domain, port));
		}

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
		return excel(file, DEFAULT_IGNORED_ROWS, sheet, column);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#formField(java.lang.String, java.lang.String)
	 */
	@Override
	public API formParam(String name, Object value)
	{
		mustNotNullOrEmpty(name, "form param name");
		mustNotNull(value, "form param value");

		given.formParam(name, value);

		return this;
	}

	@Override
	public API get(String data)
	{
		setSingleTest(HttpMethod.GET, data);
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#header(java.lang.String, java.lang.String)
	 */
	@Override
	public API header(String name, Object value)
	{
		mustNotNullOrEmpty(name, "http header field name");
		mustNotNull(value, "http header field value");

		given().header(name, value);

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
		mustNotNullOrEmpty(caseName = name, "test case name");
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#patch(java.lang.String)
	 */
	@Override
	public API patch(String data)
	{
		setSingleTest(HttpMethod.PATCH, data);
		return this;
	}

	@Override
	public API port(int port)
	{
		mustBePositive(port, "port");

		this.port = port;

		return this;
	}

	@Override
	public API post(String data)
	{
		setSingleTest(HttpMethod.POST, data);
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
		setSingleTest(HttpMethod.PUT, data);
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#resultAsCSV()
	 */
	@SuppressWarnings("resource")
	@Override
	public Summary resultAsExcel() throws IOException
	{
		// run all tests
		for (Test test : tests)
		{
			exec(test);
		}

		FileOutputStream testContent = new FileOutputStream(
				Report.forAPITesting(String.format("%s-api-testing-report-%s.xlsx", caseName, currentDateTime())));

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("api testing");

		// create head
		createRow(sheet, 0, Test.HEADS);

		// HP freeze first row
		// HP format first row

		// create content
		for (int i = 0; i < tests.size(); i++)
		{
			// +1 due to head is being first row
			createRow(sheet, i + 1, test(i).stringfyFields());
		}

		workbook.write(testContent);
		testContent.close();
		workbook.close();

		return new Summary(tests);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#returnJson()
	 */
	@Override
	public JSONObject returnJson()
	{
		return exec(firstTest()).asJson();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#returnJsonArray()
	 */
	@Override
	public JSONArray returnJsonArray()
	{
		return exec(firstTest()).asJsonArray();
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

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#upload(java.lang.String)
	 */
	@Override
	public String upload(String file)
	{
		mustNotNullOrEmpty(file, "file path");

		return given().multiPart(new File(file)).post().asString();
	}

	/**
	 * @param sheet
	 * @param i
	 * @param fields
	 */
	private void createRow(XSSFSheet sheet, int i, String[] fields)
	{
		Row row = sheet.createRow(i);

		for (int j = 0; j < fields.length; j++)
		{
			Log.info("field", fields[j]);
			if (fields[j] != null && fields[j].length() >= 32767)
			{
				fields[j] = fields[j].substring(0, 32767);
			}

			row.createCell(j).setCellValue(fields[j]);
		}
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
		if (test.invalid())
		{
			return new Response(test.result);
		}

		// exec then set result to response
		Response result = new Response(doExcel(test));
		test.setTime(result.getTime());

		return result;
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
	 * @param data
	 * @return
	 */
	private boolean setSingleTest(HttpMethod method, String data)
	{
		return tests.add(new Test(caseName, url, method, data, domain, port));
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