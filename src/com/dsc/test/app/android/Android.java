/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.android;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.SELENDROID;
import static io.appium.java_client.remote.MobileBrowserType.CHROME;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.BROWSER_NAME;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.dsc.test.app.App;

import io.appium.java_client.android.AndroidDriver;

/**
 * @Author alex
 * @CreateTime Feb 7, 2017 6:17:47 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Android extends App<Android, AndroidDriver<RemoteWebElement>>
{
	public Android()
	{
		super(DesiredCapabilities.android());
		platform(ANDROID);
		setCapability("noSign", "true");
		setCapability(BROWSER_NAME, CHROME);
	}

	// usually recommended for versions>HP ???.
	public Android forNewVersion()
	{
		setCapability(AUTOMATION_NAME, ANDROID_UIAUTOMATOR2);
		return this;
	}

	// usually recommended for versions< 4.2.
	public Android forOldVersion()
	{
		setCapability(AUTOMATION_NAME, SELENDROID);
		return this;
	}

	public Android needBrowser()
	{
		browser(CHROME);
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#createDriver(java.lang.String)
	 */
	@Override
	protected AndroidDriver<RemoteWebElement> createDriver(String remoteAddress) throws MalformedURLException
	{
		return new AndroidDriver<>(new URL(remoteAddress), cap);
	}
}