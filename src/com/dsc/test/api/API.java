/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api;

import java.io.IOException;

import com.dsc.test.api.base.ColumnCfg;
import com.dsc.test.api.base.Response;
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
	 * Set the action that will be requested
	 *
	 * @param action
	 *            the action that will be used to combined url with domain and<br>
	 *            port like:domain:port/action if url not set.
	 * @return the test runner itself
	 * @throws IllegalStateException
	 *             if url is set also
	 */
	API action(String action) throws IllegalStateException;

	/**
	 * Tell the runner use http basic authentication
	 *
	 * @param user
	 *            the user name
	 * @param password
	 *            the password
	 * @return the test runner itself
	 */
	API basicAuth(String user, String password);

	/**
	 * Specify the content type of the file
	 *
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
	 * @return the test runner itself
	 */
	API cookie(String name, Object value);

	/**
	 * Perform a DELETE request to a path that specified by
	 * {@link #url(String)}}
	 * @return the test runner itself
	 */
	Response delete();

	/**
	 * set the domain
	 *
	 * @param domain
	 *            the domain that will be used to combined url with port and<br>
	 *            action like:domain:port/action if url not set.
	 * @return the test runner itself
	 * @throws IllegalStateException
	 *             if url is set also
	 */
	API domain(String domain) throws IllegalStateException;

	/**
	 * Set an excel file as testing specifications with default configurations:
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
	 * Set an excel file as testing specification
	 *
	 * @param file
	 *            the file which contains testing specifications
	 * @param ignoredRows
	 *            the ignored rows which tell runner how many heading rows should
	 *            be ignored
	 * @param column
	 *            the column configuration that tell what each excel row
	 *            represents
	 * @return the test runner itself
	 */
	API excel(String file, int ignoredRows, ColumnCfg column);

	/**
	 * Set an excel file as testing specification
	 *
	 * @param file
	 *            the file which contains testing specifications
	 * @param ignoredRows
	 *            the ignored rows which tell runner how many heading rows should
	 *            be ignored
	 * @param sheet
	 *            the name of sheet that contains testing specifications
	 * @param column
	 *            the column configuration that tell what each excel row
	 *            represents
	 * @return the test runner itself
	 */
	API excel(String file, int ignoredRows, String sheet, ColumnCfg column);

	/**
	 * Set an excel file as testing specification
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
	 * @return the test runner itself
	 */
	Response get();

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
	 * Set the test name that will be used in logging and as report file<br>
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
	 * @return the test runner itself
	 */
	Response patch();

	/**
	 *
	 * @param port
	 *            the port that will be used to combined url with domain and
	 *            action like:domain:port/action if url not set.
	 * @return the test runner itself
	 * @throws IllegalStateException
	 *             if url is set also
	 * @return the test runner itself
	 */
	API port(int port) throws IllegalStateException;

	/**
	 * Perform a POST request to a path that specified by
	 * {@link #url(String)}}
	 * @return the test runner itself
	 */
	Response post();

	/**
	 * Perform a PUT request to a path that specified by
	 * {@link #url(String)}}
	 *
	 * @return the test runner itself
	 */
	Response put();

	/**
	 * Run batched tests and generate a report to project/report/api <br>
	 * Use {@link #returnSummary()} if you don't want to generate detailed
	 * report<br>
	 * Use {@link #resultAsExcel()} if you want to generate detailed report that
	 * will take time
	 *
	 * @return the summary of test result
	 * @throws IOException
	 *             on write out report failed
	 */
	Summary resultAsExcel() throws IOException;


	/**
	 * Run batched tests and return result summary <br>
	 * Use {@link #returnSummary()} if you don't want to generate detailed
	 * report<br>
	 * Use {@link #resultAsExcel()} if you want to generate detailed report that
	 * will take time
	 *
	 * @return the summary of testing result
	 */
	Summary returnSummary();

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
	 * set the url as the endpoint that will be requested to
	 *
	 * @param url
	 *            the url
	 * @return the test runner itself
	 */
	API url(String url);
}
