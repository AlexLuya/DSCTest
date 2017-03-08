/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dsc.test.api.base.ColumnCfg;
import com.dsc.test.common.report.Summary;
import com.dsc.util.ContentType;


/**
 * The API Test Runner
 *
 * @Author alex
 * @CreateTime 22.02.2017 09:33:30
 * @Version 1.0
 * @Since 1.0
 */
public interface API
{

	/** initialize an implementation */
	API test = new APITestImpl();

	/**
	 * tell test use http basic authentication
	 *
	 * @param user
	 *            the user name
	 * @param password
	 *            the password
	 * @return the test runner itself
	 */
	API basicAuth(String user, String password);

	/**
	 *Specify the content type of the file which will be uploaded
	 * @param type
	 *            the content type
	 * @return the test runner itself
	 */
	API contentType(ContentType type);

	/**
	 * set cookie
	 *
	 * @param name
	 *            the cookie name
	 * @param value
	 *            the cookie value
	 * @return the test runner itself the test runner itself
	 */
	API cookie(String name, Object value);

	/**
	 * Perform a DELETE request to a path that specified by
	 * {@link #url(String)}}
	 *
	 * @param data
	 *            the data that will be used to replace path params,or be
	 *            appended to the url if non path param should be replaced and
	 *            data!=null
	 * @return the test runner itself
	 */
	API delete(String data);

	/**
	 * set the domain that will be used to combined url with port if url not set
	 *
	 * @param domain
	 *            the domain
	 * @return the test runner itself
	 */
	API domain(String domain);

	/**
	 * Set an excel file as testing specifications with default configurations:
	 * <ul>
	 * <li>Sheet: first sheet</li>
	 * <li>ignoreRows: first row</li>
	 * <li>columConfig: @see {@link com.dsc.test.api.base.ColumnConfig}'default
	 * field values</li>
	 * </ul>
	 *
	 * @param file
	 *            the file which contains testing specifications
	 * @return the test runner itself
	 */
	API excel(String file);

	/**
	 * Set an excel file as testing specifications
	 *
	 * @param file
	 *            the file which contains testing specifications
	 * @param column
	 *            the column configuration that tell what each excel row
	 *            represents
	 * @return the test runner itself
	 */
	API excel(String file, ColumnCfg column);

	/**
	 * Set an excel file as testing specifications
	 *
	 * @param file
	 *            the file which contains testing specifications
	 * @param ignoredRows
	 *            the ignored rows which tell runner how many heading rows will
	 *            be ignored in the excel file
	 * @param column
	 *            the column configuration that tell what each excel row
	 *            represents
	 * @return the test runner itself
	 */
	API excel(String file, int ignoredRows, ColumnCfg column);

	/**
	 * Set an excel file as testing specifications
	 *
	 * @param file
	 *            the file which contains testing specifications
	 * @param ignoredRows
	 *            the ignored rows which tell runner how many heading rows will
	 *            be ignored in the excel file
	 * @param sheet
	 *            the name of sheet that contains testing specifications
	 * @param column
	 *            the column configuration that tell what each excel row
	 *            represents
	 * @return the test runner itself
	 */
	API excel(String file, int ignoredRows, String sheet, ColumnCfg column);

	/**
	 * Set an excel file as testing specifications
	 *
	 * @param file
	 *            the file which contains testing specifications
	 * @param sheet
	 *            the name of sheet that contains testing specifications
	 * @param column
	 *            the column configuration that tell what each excel row
	 *            represents
	 * @return the test runner itself
	 */
	API excel(String file, String sheet, ColumnCfg column);

	/**
	 * Specify a form parameter that'll be sent with the request
	 *
	 * @param name
	 *            The parameter name
	 * @param value
	 *            Zero to many parameter values. You can specify multiple values
	 *            for the same parameter.
	 * @return the test runner itself
	 */
	API formParam(String name, Object value);

	/**
	 * Perform a GET request to a path that specified by
	 * {@link #url(String)}}
	 *
	 * @param data
	 *            the data that will be used to replace path params,or be
	 *            appended to the url if non path param should be replaced and
	 *            data!=null
	 * @return the test runner itself
	 */
	API get(String data);

	/**
	 * Specify a header that'll be sent with the http request head
	 *
	 * @param name
	 *            The header name
	 * @param value
	 *            The header value
	 * @return the test runner itself
	 */
	API header(String name, Object value);

	/**
	 * Set the test name that will be used in logging and as report file
	 * prefix,a default name will be given if not set,that is
	 * {@link com.dsc.test.api.base.Test#NON_NAMED_API_TEST}
	 *
	 * @param name
	 *            the test name
	 * @return the test runner itself
	 */
	API name(String name);

	/**
	 * Perform a PATCH request to a path that specified by
	 * {@link #url(String)}}
	 *
	 * @param data
	 *            the data that will be used to replace path params,or be
	 *            appended to the url if non path param should be replaced and
	 *            data!=null
	 * @return the test runner itself
	 */
	API patch(String data);

	/**
	 * set the domain that will be used to combined url with domain if url not
	 * set
	 *
	 * @param port
	 *            the port
	 * @return the test runner itself
	 */
	API port(int port);

	/**
	 * Perform a POST request to a path that specified by
	 * {@link #url(String)}}
	 *
	 * @param data
	 *            the data that will be used to replace path params,or be
	 *            appended to the url if non path param should be replaced and
	 *            data!=null
	 * @return the test runner itself
	 */
	API post(String data);

	/**
	 * Perform a PUT request to a path that specified by
	 * {@link #url(String)}}
	 *
	 * @param data
	 *            the data that will be used to replace path params,or be
	 *            appended to the url if non path param should be replaced and
	 *            data!=null
	 * @return the test runner itself
	 */
	API put(String data);

	/**
	 * Run tests and write out result as an excel in under
	 * dir:project/report/api
	 *
	 * @return the summary of test result
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	Summary resultAsExcel() throws IOException;

	/**
	 * Run test and return result as a JSONObject
	 *
	 * @return the JSON object of result
	 */
	JSONObject returnJson();

	/**
	 * Return json array.
	 *
	 * @return the JSON array
	 */
	JSONArray returnJsonArray();

	/**
	 * Run test and return result as a string
	 *
	 * @return the string of result
	 */
	String returnString();

	/**
	 * Upload specified file
	 *
	 * @param file
	 *            the file to be uploaded
	 * @return the result returned by server,or status code if no explicit
	 *         result returned
	 */
	String upload(String file);

	/**
	 * Set the url that will be requested
	 * the combination of domain and port will be used if not set
	 *
	 * @param url
	 *            the url
	 * @return the test runner itself
	 */
	API url(String url);
}
