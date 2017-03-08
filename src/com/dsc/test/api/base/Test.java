/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api.base;

import static com.dsc.test.api.base.HttpMethod.GET;
import static com.dsc.util.StringUtil.stringfy;
import static com.dsc.util.StringUtil.toUTF8;
import static com.dsc.util.StringUtil.unformat;
import static com.dsc.util.Util.notNullOrEmpty;
import static com.dsc.util.Util.nullOrEmpty;
import static com.dsc.util.Util.params;
import static com.dsc.util.Util.stripLeadingAndTailWhitespace;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

import com.dsc.util.FileUtil;
import com.dsc.util.Json;

/**
 * @Author alex
 * @CreateTime 02.03.2017 13:37:57
 * @Version 1.0
 * @Since 1.0
 */
public class Test
{
	public static final String[]	HEADS				= new String[] { "Case", "URL", "Method", "Data", "Time", "Expectation",
			"Result", "Diff" };
	// private static final String HTTP = "http";

	private static final String		NON_NAMED_API_TEST	= "NON_NAMED_API_TEST";
	// private static final String WWW = "www";

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
		if (ColumnCfg.columnNofExisted(idx))
		{
			throw null;
		} else if (idx >= fields.size())
		{
			throw new RuntimeException(String.format("%dth column specified in config but not existed in data", idx));
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
	public Object		expectation;
	public HttpMethod	method;
	public String		result;
	public String		url;
	public String		violation;
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
				obj(fields, column.expectation), domain, port);
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
	public Test(String caseName, String url, HttpMethod method, Object data, Object expectation, String domain, int port)
	{
		this.caseName = caseName;
		if (null == caseName)
		{
			this.caseName = NON_NAMED_API_TEST;
		}
		this.expectation = Json.formatIfItIs(expectation);
		this.method = method;
		this.domain = toUTF8(domain);
		this.port = port;
		setUpUrl(url);
		setUpData(data);
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

	public boolean dataIsJson()
	{
		// if (method != POST)
		// {
		// return false;
		// }

		if (data == null)
		{
			return false;
		}

		if (Json.not(stringfy(data)))
		{
			return false;
		}

		return true;
	}

	public String diff()
	{
		return StringUtils.difference(stringfy(expectation), result);
	}

	/**
	 * @return
	 */
	public boolean invalid()
	{
		if (!"".equals(invalidFields()))
		{
			violation = "Ignored due to Must-have field: " + invalidFields() + " is null or emtpy";
		}

		return violation != null;
	}

	/**
	 * @return
	 */
	public boolean isUploading()
	{
		// if url contains "upload" and data represent a file
		if (url.toLowerCase().contains("upload") && FileUtil.existed(data))
		{
			return true;
		}

		return false;
	}

	/**
	 * @param error
	 */
	public void setResult(String result)
	{
		this.result = Json.formatIfItIs(result);
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
		return new String[] { caseName, url, method == null ? null : method.toString(), stringfy(data), Float.toString(time()),
				stringfy(expectation), result, diff() };
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
		this.data = toUTF8(data);

		if (url == null)
		{
			return;
		} else if (paramCount() > 0)
		{
			tryToReplacePathParams(data);
		} else if (data != null && method == GET && !url.contains(toUTF8(data)))
		{
			url = url + toUTF8(data);
		}
	}

	/**
	 * @return
	 */
	private void setUpUrl(String url)
	{
		this.url = toUTF8(url);

		if (this.url == null)
		{
			if (notNullOrEmpty(domain))
			{
				this.url = domain + ":" + port;
			} else
			{
				violation = "Both url and domain is null or empty";
			}
		}

		if (!UrlValidator.getInstance().isValid(this.url))
		{
			violation = "url is invalid";
		}
	}

	/**
	 * @param data
	 */
	private void tryToReplacePathParams(Object data)
	{
		if (data == null)
		{
			violation = "data==null,so nothing to replace:" + String.join(",", params(url)) + " in the url";
		} else if (paramCount() != dataCount())
		{
			violation = String.format("param count:%d!=%d:data count", paramCount(), dataCount());
		} else
		{
			url = StringUtils.replaceEach(url, params(url), unformat(stringfy(data)).split(","));
		}
	}
}