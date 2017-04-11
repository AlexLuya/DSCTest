/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.android;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.SELENDROID;
import static io.appium.java_client.remote.MobileBrowserType.CHROME;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

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
	}

	@Override
	public Android activity(String activity)
	{
		setCapability(APP_ACTIVITY, activity);
		return this;
	}

	@Override
	public String browserName()
	{
		return CHROME;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.dsc.test.common.Context#decorator(org.openqa.selenium.SearchContext)
	 */
	@Override
	public FieldDecorator decorator(SearchContext searchCxt)
	{
		return new AndroidFieldDecorator(this, searchCxt);
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

	/* (non-Javadoc)
	 * @see com.dsc.test.app.App#isLocked()
	 */
	@Override
	public boolean isLocked()
	{
		return driver.isLocked();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#lockDevice(int)
	 */
	@Override
	public void lockDevice(int seconds)
	{
		driver.lockDevice();
	}

	//	@Override
	//	public Android launch(String pkg)
	//	{
	//		setCapability(APP_PACKAGE, pkg);
	//		return this;
	//	}

	public void openNotification()
	{
		driver.openNotifications();
	}

	public void pushFile(String file, String toWhere) throws IOException
	{
		// HP validate args
		driver.pushFile(toWhere, Files.readAllBytes(Paths.get(file)));
	}

	public void sendKey(int key)
	{
		driver.pressKeyCode(key);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#shake()
	 */
	@Override
	public void shake()
	{
		// IOS only,not applied for android
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#UDID(java.lang.String)
	 */
	@Override
	public Android UDID(String udid)
	{
		// IOS only,not applied for android
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.app.App#unlockDevice()
	 */
	@Override
	public void unlockDevice()
	{
		driver.unlockDevice();
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

	/* (non-Javadoc)
	 * @see com.dsc.test.app.App#pageId()
	 */
	@Override
	protected String pageId()
	{
		return APP_ACTIVITY;
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.app.App#pkgOrBundleId()
	 */
	@Override
	protected String pkgOrBundle()
	{
		return APP_PACKAGE;
	}
}