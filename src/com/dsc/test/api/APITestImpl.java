/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api;

import static com.dsc.util.ContentType.JSON;
import static com.dsc.util.Util.currentDateTime;
import static com.dsc.util.Util.mustBePositive;
import static com.dsc.util.Util.mustNotNull;
import static com.dsc.util.Util.mustNotNullOrEmpty;
import static com.dsc.util.Util.notEmpty;
import static java.lang.String.format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dsc.test.api.base.ColumnCfg;
import com.dsc.test.api.base.HttpMethod;
import com.dsc.test.api.base.Response;
import com.dsc.test.api.base.Test;
import com.dsc.test.common.report.Report;
import com.dsc.test.common.report.Summary;
import com.dsc.util.ContentType;
import com.dsc.util.Excel;
import com.dsc.util.FileUtil;
import com.dsc.util.Log;
import com.dsc.util.common.model.NameValue;
import com.google.common.collect.Lists;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
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
	private String					action;
	private String					caseName;
	private String					domain;
	private RequestSpecification	given;
	private int						port					= 80;
	private List<Test>				tests					= Lists.newArrayList();
	private String					url;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#url(java.lang.String)
	 */
	@Override
	public APITestImpl action(String action)
	{
		mustNotNullOrEmpty(this.action = action, "action");
		return this;
	}

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
	 * @see com.dsc.test.api.API#contentType(com.dsc.test.api.ContentType)
	 */
	@Override
	public APITestImpl contentType(ContentType type)
	{
		given().contentType(type.toString());
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

		given().cookie(name, value);

		return this;
	}

	@Override
	public Response delete()
	{
		return setSingleTest(HttpMethod.DELETE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#domain(java.lang.String)
	 */
	@Override
	public API domain(String domain) throws IllegalStateException
	{
		mustNotNullOrEmpty(this.domain = domain, "domain name");
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

		for (int i = ignoredRows; i <excel.lastNonemptyRow(); i++)
		{
			tests.add(new Test(excel.getDataFromRow(i), column, url()));
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

		given().formParam(name, value);

		return this;
	}

	@Override
	public Response get()
	{
		return setSingleTest(HttpMethod.GET);
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
	public Response patch()
	{
		return setSingleTest(HttpMethod.PATCH);
	}

	@Override
	public API port(int port)
	{
		mustBePositive(port, "port");

		this.port = port;

		return this;
	}

	@Override
	public Response post()
	{
		return setSingleTest(HttpMethod.POST);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#put(java.lang.String)
	 */
	@Override
	public Response put()
	{
		return setSingleTest(HttpMethod.PUT);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#resultAsCSV()
	 */
	@Override
	public Summary resultAsExcel() throws IOException
	{
		// run all tests
		Summary summary = returnSummary();

		Log.debug("Generate report ...");

		writeOutTestsAsExcel();

		Log.debug("The report generated under dir:report/api sucessfully!!!");

		return summary;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.api.API#returnSummary()
	 */
	@Override
	public Summary returnSummary()
	{
		// run all tests
		for (Test test : tests)
		{
			exec(test);
		}

		return new Summary(tests);
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

		io.restassured.response.Response response = doUploading(file);

		if (notEmpty(response.asString()))
		{
			return response.asString();
		}

		return Integer.toString(response.getStatusCode());
	}

	@Override
	public API url(String url)
	{
		mustNotNullOrEmpty(this.url = url, "url");

		return this;
	}

	/**
	 * @param sheet
	 * @param i
	 * @param fields
	 */
	private Row createRow(XSSFSheet sheet, int i, String[] fields)
	{
		Row row = sheet.createRow(i);

		for (int j = 0; j < fields.length; j++)
		{
			if (fields[j] != null && fields[j].length() >= 32767)
			{
				// execl cell'max size is 32767
				fields[j] = fields[j].substring(0, 32767);
			}

			row.createCell(j).setCellValue(fields[j]);
		}

		return row;
	}

	/**
	 * @param test
	 * @return
	 */
	private Response doExec(Test test)
	{
		if (test.isUploading())
		{
			// test as uploading
			Log.warn(format("Executing %dth api test: %s as uploading", tests.indexOf(test), test.caseName));
			return new Response(action(test.url()).doUploading(test.data.toString()));
		}

		return new Response(doExecNonUploading(test));
	}

	private io.restassured.response.Response doExecNonUploading(Test test)
	{
		switch (test.method)
		{
			case DELETE:
				return given().delete(test.url());
			case GET:
				return given().get(test.url());
			case PATCH:
				return given().patch(test.url());
			case POST:
				return post(test, test.url());
			case PUT:
				return given().post(test.url());
			default:
				throw new RuntimeException(test.method + " isn't a supported http method");
		}
	}

	/**
	 * @param file
	 * @return
	 */
	private io.restassured.response.Response doUploading(String file)
	{
		file = FileUtil.tryToAbsolutionPath(file);

		return given().multiPart(new File(file)).post(url());
	}

	private Response exec(Test test)
	{
		if (test.invalid())
		{
			Log.debug(format("Ignored api test: %s due to: %s", test.caseName, test.result));
			return new Response(test.violation);
		}

		// exec then set result to response
		Response result = doExec(test);
		test.setTime(result.getTime());
		test.setResult(result.asString());

		Log.debug(format("Executing %dth api test: %s in %f seconds", tests.indexOf(test), test.caseName, test.time()));

		return result;
	}

	/**
	 * @return
	 */
	private RequestSpecification given()
	{
		if (given == null)
		{
			given = RestAssured.given()
					.config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8")));
			// config(RestAssured.config().decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8")));
		}

		return given;
	}

	/**
	 * @param test
	 * @param url
	 * @return
	 */
	private io.restassured.response.Response post(Test test, String url)
	{
		if (test.dataIsJson())
		{
			return given().contentType(JSON.toString()).body(test.data.toString()).post(url);
		}

		for (NameValue pair : test.dataAsParams())
		{
			formParam(pair.getName(), pair.getValue());
		}

		return given().request().post(url);
	}

	/**
	 * @param data
	 * @return
	 */
	private Response setSingleTest(HttpMethod method)
	{
		tests.add(new Test(caseName, url(), action, method, null));

		return exec(tests.get(0));
	}

	/**
	 * @param index
	 * @return
	 */
	private Test test(int index)
	{
		return tests.get(index);
	}

	/**
	 * @return
	 */
	private String url()
	{
		// both not set
		if (null == url && null == domain)
		{
			throw new RuntimeException("Both domain and url don't set,can't determine where to request");
		}

		// both set
		if (null != url && null != domain)
		{
			throw new RuntimeException("Both domain and url set,can't determine which one to request");
		}

		if (url == null)
		{
			return domain + ":" + port;
		}

		return url;
	}

	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void writeOutTestsAsExcel() throws FileNotFoundException, IOException
	{
		@SuppressWarnings("resource")
		FileOutputStream testContent = new FileOutputStream(
				Report.forAPITesting(format("%s-api-testing-report-%s.xlsx", caseName, currentDateTime())));

		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("api testing");

		// create head
		Row headRow = createRow(sheet, 0, Test.HEADS);

		// freeze first row and first column
		sheet.createFreezePane(1, 1);
		// format first row
		Excel.setBold(workbook, headRow);
		Excel.setColor(workbook, headRow, IndexedColors.BLUE, IndexedColors.BLUE_GREY);

		// create content
		for (int i = 0; i < tests.size(); i++)
		{
			// +1 due to head is being first row
			createRow(sheet, i + 1, test(i).stringfyFields());
		}

		workbook.write(testContent);
		testContent.close();
		workbook.close();
	}
}