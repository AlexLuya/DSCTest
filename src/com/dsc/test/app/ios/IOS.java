/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ios;

import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;
import static io.appium.java_client.remote.MobileBrowserType.SAFARI;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.BROWSER_NAME;
import static io.appium.java_client.remote.MobilePlatform.IOS;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.dsc.test.app.App;

import io.appium.java_client.ios.IOSDriver;

/**
 * @Author alex
 * @CreateTime Feb 11, 2017 11:34:16 PM
 * @Version 1.0
 * @Since 1.0
 */
public class IOS extends App<IOS, IOSDriver<RemoteWebElement>>
{
	public IOS()
	{
		super(DesiredCapabilities.iphone());
		platform(IOS);
		setCapability(BROWSER_NAME, SAFARI);
	}

	// usually recommended for versions>10.X.
	public IOS forNewVersion()
	{
		setCapability(AUTOMATION_NAME, IOS_XCUI_TEST);
		return this;
	}

	public IOS needBrowser()
	{
		browser(SAFARI);
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#createDriver(java.lang.String)
	 */
	@Override
	protected IOSDriver<RemoteWebElement> createDriver(String remoteAddress) throws MalformedURLException
	{
		return new IOSDriver<>(new URL(remoteAddress), cap);
	}
}