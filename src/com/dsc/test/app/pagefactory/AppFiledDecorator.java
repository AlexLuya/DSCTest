/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.pagefactory;

import org.openqa.selenium.SearchContext;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @Author alex
 * @CreateTime 23.02.2017 15:15:31
 * @Version 1.0
 * @Since 1.0
 */
public class AppFiledDecorator extends AppiumFieldDecorator
{

	/**
	 * @param context
	 */
	public AppFiledDecorator(SearchContext context)
	{
		super(context);
	}

}
