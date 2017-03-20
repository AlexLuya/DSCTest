/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api.base;

import static com.dsc.test.api.base.HttpMethod.GET;
import static com.dsc.util.StringUtil.stringfy;
import static com.dsc.util.StringUtil.stripLeadingAndTailWhitespace;
import static com.dsc.util.StringUtil.toUTF8;
import static com.dsc.util.StringUtil.unformat;
import static com.dsc.util.Util.mustNotNullOrEmpty;
import static com.dsc.util.Util.notEmpty;
import static com.dsc.util.Util.nullOrEmpty;
import static com.dsc.util.Util.params;
import static com.dsc.util.Util.tryToPrefix;
import static java.lang.String.format;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

import com.dsc.util.FileUtil;
import com.dsc.util.Json;
import com.dsc.util.Log;
import com.dsc.util.StringUtil;
import com.dsc.util.common.model.NameValue;

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
			return null;
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
	public String		violation;
	private long		time	= 0;
	private String		url;

	/**
	 * @param fields
	 * @param column
	 * @param url
	 * @param port
	 */
	public Test(List<Object> fields, ColumnCfg column, String url)
	{
		this(str(fields, column.caseName), url, str(fields, column.action), method(fields, column.method),
				obj(fields, column.data), obj(fields, column.expectation));
	}

	/**
	 * @param caseName
	 * @param url
	 * @param action
	 * @param data
	 * @param method
	 * @param port
	 */
	public Test(String caseName, String url, String action, HttpMethod method, Object data)
	{
		this(caseName, url, action, method, data, null);
	}

	/**
	 * @param caseName
	 * @param data
	 * @param expectation
	 * @param method
	 * @param url
	 * @param port
	 */
	public Test(String caseName, String url, String action, HttpMethod method, Object data, Object expectation)
	{
		this.caseName = caseName == null ? NON_NAMED_API_TEST : caseName;
		mustNotNullOrEmpty(this.url = toUTF8(url), "nurl");
		this.expectation = Json.formatIfItIs(expectation);
		this.method = method;
		setUpAction(action);
		setUpData(data);
	}

	/**
	 * @return
	 */
	public List<NameValue> dataAsParams()
	{
		return NameValue.parseAsList(data);
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
		//HP printed time is far more short then actual time
		return (float) time / 1000;
	}

	/**
	 * @return
	 */
	public String url()
	{
		return StringUtil.unformat(url);
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
	 * @return
	 */
	private void setUpAction(String action)
	{
		if (notEmpty(action))
		{
			url = url + tryToPrefix(action, "/");
		}

		if (!UrlValidator.getInstance().isValid(this.url))
		{
			violation = "url is invalid";
		}
	}

	/**
	 * @param data
	 */
	private void setUpData(Object data)
	{
		this.data = toUTF8(data);

		if (method == GET)
		{
			setupQuery(data);
		}
	}

	/**
	 * @param data
	 */
	private void setupQuery(Object data)
	{
		if (paramCount() > 0)
		{
			tryToReplaceQueryParams(data);
		} else if (shouldBeAppendedAsQuerying(data))
		{
			url = url + tryToPrefix(data.toString(), "?");
		} else if (data != null && url.contains(data.toString()))
		{
			Log.warn(format("Data %s ignored due to contained in url already", stringfy(this.data)));
		}
	}

	/**
	 * @param data
	 */
	private boolean shouldBeAppendedAsQuerying(Object data)
	{
		if (data == null || url.contains(data.toString()))
		{
			return false;
		}

		// it must be format like key=value,so length must >=3
		return data.toString().split("=").length > 2;
	}

	/**
	 * @param data
	 */
	private void tryToReplaceQueryParams(Object data)
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