/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app;

import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.BROWSER_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_NAME;
import static org.openqa.selenium.remote.CapabilityType.TAKES_SCREENSHOT;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.dsc.test.common.Context;
import com.dsc.util.Util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

/**
 * @Author alex
 * @CreateTime Feb 8, 2017 10:54:48 AM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class App<T extends App<T, D>, D extends AppiumDriver<RemoteWebElement>> extends Context<T,D>
{
	private static final String		NATIVE_APP	= "NATIVE_APP";
	private static final String		WEBVIEW		= "WEBVIEW_";


	public App(DesiredCapabilities cap)
	{
		super(cap);
		setCapability(AUTOMATION_NAME, "Appium");
		setCapability(TAKES_SCREENSHOT, "true");
	}

	public App(String appFilePath,DesiredCapabilities cap)
	{
		this(cap);
		Util.mustNotNull("apk path", appFilePath);
		install(appFilePath);

		setCapability(AUTOMATION_NAME, "Appium");
		setCapability(TAKES_SCREENSHOT, "true");
	}

	@SuppressWarnings("unchecked")
	public T browser(String browser)
	{
		setCapability(BROWSER_NAME, browser);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T deviceName(String name)
	{
		setCapability(DEVICE_NAME, name);
		return (T) this;
	}

	public MultiTouchAction MultiTouchAction()
	{
		return new MultiTouchAction(driver);
	}

	@SuppressWarnings("unchecked")
	public T platform(String platform)
	{
		setCapability(PLATFORM_NAME, platform);
		return (T) this;
	}

	public void startServer(String remoteAddress) throws MalformedURLException
	{
		Util.mustNotNull("remote address", remoteAddress);

		// HP ensure neccessary capabilities got set already
		// device name

		driver = createDriver(remoteAddress);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@SuppressWarnings("unchecked")
	public T toNative()
	{
		driver.context(NATIVE_APP);
		return (T) this;
	}

	public TouchAction touch()
	{
		return new TouchAction(driver);
	}

	@SuppressWarnings("unchecked")
	public T toWebView()
	{
		driver.context(WEBVIEW + 1);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T toWebView(int index)
	{
		driver.context(WEBVIEW + index);
		return (T) this;
	}

	/**
	 * @param remoteAddress
	 * @return
	 * @throws MalformedURLException
	 */
	protected abstract D createDriver(String remoteAddress) throws MalformedURLException;



	@SuppressWarnings("unchecked")
	private T install(String appFilePath)
	{
		setCapability(APP, new File(appFilePath).getAbsolutePath());
		return (T) this;
	}
}