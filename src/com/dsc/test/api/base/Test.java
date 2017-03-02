/**
 * Copyright (c) (2010-2020),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.api.base;

import static com.dsc.util.Util.nullOrEmpty;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author alex
 * @CreateTime 02.03.2017 13:37:57
 * @Version 1.0
 * @Since 1.0
 */
public class Test
{
	public String		caseName;
	public Object		data;
	public String		expectation;
	public HttpMethod	method;
	public String		result;
	public String		url;
	private String		domain;
	private int			port;

	public String diff()
	{
		return StringUtils.difference(expectation, result);
	}

	/**
	 * @return
	 */
	public String invalidField()
	{
		if (nullOrEmpty(url))
		{
			return "url";
		}
		// if(method==null)
		// {
		// return "method";
		// }
		// if(nullOrEmpty())
		// {
		// return "";
		// }
		// if(nullOrEmpty())
		// {
		// return "";
		// }

		return null;
	}

	/**
	 * @return
	 */
	private String url()
	{
		return domain + ":" + port + "/" + data;
	}
}
