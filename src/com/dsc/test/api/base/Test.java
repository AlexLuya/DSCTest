/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api.base;

import static com.dsc.util.Util.notNullOrEmpty;
import static com.dsc.util.Util.nullOrEmpty;
import static com.dsc.util.Util.params;
import static com.dsc.util.Util.stringfy;
import static com.dsc.util.Util.stripLeadingAndTailWhitespace;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author alex
 * @CreateTime 02.03.2017 13:37:57
 * @Version 1.0
 * @Since 1.0
 */
public class Test
{
	public static final String[]	HEADS	= new String[] { "Case", "URL", "Method", "Data", "Expectation", "Result", "Time",
	"Diff" };
	private static final String		HTTP	= "http";

	private static final String		WWW		= "www";

	private static HttpMethod method(List<Object> fields, int method)
	{
		if (obj(fields, method) == null)
		{
			return null;
		}
		return HttpMethod.valueOf(str(fields, method));
	}

	/**
	 * @param fields
	 * @param idx
	 */
	private static Object obj(List<Object> fields, int idx)
	{
		if (idx >= fields.size())
		{
			// HP tell column
			throw null;
		}

		return fields.get(idx);
	}

	private static String str(List<Object> fields, int idx)
	{
		if (obj(fields, idx) == null)
		{
			return null;
		}

		return stripLeadingAndTailWhitespace((String) fields.get(idx));
	}

	public String		caseName;
	public Object		data;
	public String		expectation;
	public HttpMethod	method;
	public String		result;
	public String		url;
	private String		domain;

	private int			port	= 80;
	private long		time	= 0;

	/**
	 * @param fields
	 * @param column
	 * @param domain
	 * @param port
	 */
	public Test(List<Object> fields, ColumnCfg column, String domain, int port)
	{
		this(str(fields, column.caseName), str(fields, column.url), method(fields, column.method), obj(fields, column.data),
				domain, port);
	}

	/**
	 * @param caseName
	 * @param data
	 * @param method
	 * @param url
	 * @param domain
	 * @param port
	 */
	public Test(String caseName, String url, HttpMethod method, Object data, String domain, int port)
	{
		this(caseName, url, method, data, null, domain, port);
	}

	/**
	 * @param caseName
	 * @param data
	 * @param expectation
	 * @param method
	 * @param url
	 * @param domain
	 * @param port
	 */
	public Test(String caseName, String url, HttpMethod method, Object data, String expectation, String domain, int port)
	{
		this.caseName = caseName;
		this.expectation = expectation;
		this.method = method;
		this.domain = domain;
		this.port = port;
		setUpUrl(url);
		setUpData(data);
	}

	public String diff()
	{
		return StringUtils.difference(expectation, result);
	}

	/**
	 * @return
	 */
	public boolean invalid()
	{
		if (!"".equals(invalidFields()))
		{
			result = "Ignored due to Must-have field: " + invalidFields() + " is null or emtpy";
		}

		return result != null;
	}

	/**
	 * @param error
	 */
	public void setResult(String result)
	{
		this.result = result;
	}

	/**
	 * <<<<<<< HEAD
	 *
	 * @param params
	 *            =======
	 * @param time
	 *            the time to set
	 */
	public final void setTime(long time)
	{
		this.time = time;
	}

	/**
	 * @return
	 */
	public String[] stringfyFields()
	{
		return new String[] { caseName, url, method.toString(), stringfy(data), expectation, result, Float.toString(time()),
				diff() };
	}

	/**
	 * @return
	 */
	public float time()
	{
		return (float) time / 1000;
	}

	/**
	 * @param data
	 * @return
	 */
	private int dataCount()
	{
		if (data == null)
		{
			return 0;
		}

		return stringfy(data).split(",").length;
	}

	/**
	 * @return
	 */
	private String invalidFields()
	{
		String invalids = "";

		if (nullOrEmpty(url))
		{
			invalids = "url";
		}

		if (method == null)
		{
			invalids = invalids + ",method";
		}

		// if(nullOrEmpty())
		// {
		// return "";
		// }

		// if(nullOrEmpty())
		// {
		// return "";
		// }

		return invalids;
	}

	/**
	 * >>>>>>> c198a7280159aa785993476bd47595370628f65a
	 *
	 * @return
	 */
	private int paramCount()
	{
		return params(url).length;
	}

	/**
	 * @param data
	 */
	private void setUpData(Object data)
	{
		this.data = data;

		// if nothing to be replaced
		if (paramCount() == 0)
		{
			return;
		}

		if (data == null)
		{
			result = "data==null,so nothing to replace:" + String.join(",", params(url)) + " in the url";
		} else if (paramCount() != dataCount())
		{
			result = String.format("param count:%d!=%d:data count", paramCount(), dataCount());
		} else
		{
			url = StringUtils.replaceEach(url, params(url), stringfy(data).split(","));
		}
	}

	/**
	 * @return
	 */
	private void setUpUrl(String url)
	{
		if (url == null)
		{
			return;
		} else if (url.startsWith(HTTP) || url.startsWith(WWW) || url.startsWith(domain))
		{
			// HP use library to validate URL
			this.url = url;
		} else if (notNullOrEmpty(domain))
		{
			this.url = domain + ":" + port;
		} else
		{
			result = "Neither 'url' nor 'domain' contains like:'www.xxx.com'";
		}
	}
}