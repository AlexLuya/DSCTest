/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

/**
 * @Author alex
 * @CreateTime Feb 8, 2017 10:54:48 AM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class App
{
	private static final String	NATIVE_APP	= "NATIVE_APP";
	private static final String	WEBVIEW		= "WEBVIEW_";

	private AppiumDriver<?>		driver;

	public MultiTouchAction MultiTouchAction()
	{
		return new MultiTouchAction(driver);
	}

	public App toNative()
	{

		driver.context(NATIVE_APP);

		return this;
	}

	public TouchAction touch()
	{
		return new TouchAction(driver);
	}

	public App toWebView()
	{

		driver.context(WEBVIEW + 1);

		return this;
	}

	public App toWebView(int index)
	{

		driver.context(WEBVIEW + index);

		return this;
	}
}
