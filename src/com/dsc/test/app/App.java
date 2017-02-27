/**
 * Copyright (c) (2010-2013),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app;

import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.BROWSER_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.FULL_RESET;
import static io.appium.java_client.remote.MobileCapabilityType.NEW_COMMAND_TIMEOUT;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_VERSION;
import static org.openqa.selenium.remote.CapabilityType.TAKES_SCREENSHOT;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.dsc.test.common.Context;
import com.dsc.util.Util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

/**
 * The Class App.
 *
 * @param <T>
 *            the generic type
 * @param <D>
 *            the generic type
 * @Author alex
 * @CreateTime Feb 8, 2017 10:54:48 AM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class App<T extends App<T, D>, D extends AppiumDriver<RemoteWebElement>> extends Context<T, D>
{

	/** The Constant NATIVE_APP. */
	private static final String	NATIVE_APP		= "NATIVE_APP";

	/** The Constant WEBVIEW. */
	private static final String	WEBVIEW			= "WEBVIEW_";

	/** The remote address. */
	private String				remoteAddress	= "http://127.0.0.1:4723/wd/hub";

	/**
	 * Instantiates a new app.
	 *
	 * @param cap
	 *            the cap
	 */
	public App(DesiredCapabilities cap)
	{
		super(cap);
		setWaitSeconds(5);
		// setCapability("noReset","true");
		// Reset state by clearing data rather then uninstalling to prevent
		// re-installing between sessions.
		setCapability(FULL_RESET, "false");
		// NP check whether Appium server started
		setCapability(AUTOMATION_NAME, "Appium");
		setCapability(TAKES_SCREENSHOT, "true");
		// appium exit after 30 seconds idle
		setCapability(NEW_COMMAND_TIMEOUT, "30");
	}

	public abstract T activity(String activity);

	/**
	 * Browser.
	 *
	 * @param browser
	 *            the browser
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public T browser(String browser)
	{
		Util.mustNotNull("browser", browser);
		setCapability(BROWSER_NAME, browser);
		return (T) this;
	}

	public Set<String> contexts()
	{
		return driver.getContextHandles();
	}

	/**
	 * Device name.
	 *
	 * @param name
	 *            the name
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public T deviceName(String name)
	{
		setCapability(DEVICE_NAME, name);
		return (T) this;
	}

	public Keyboard getKeyboard()
	{
		return driver.getKeyboard();
	}

	public void hideKeyboard()
	{
		driver.hideKeyboard();
	}

	public boolean isInstalled(String bundleId)
	{
		return driver.isAppInstalled(bundleId);
	}

	public boolean isKeyboardHidden()
	{
		try
		{
			driver.hideKeyboard();
			driver.getKeyboard().pressKey("c");;
		} catch (Exception e)
		{
			return true;
		}

		return false;
	}

	public abstract boolean isLocked();

	public abstract void lockDevice(int seconds);

	/**
	 * Multi touch action.
	 *
	 * @return the multi touch action
	 */
	public MultiTouchAction MultiTouchAction()
	{
		return new MultiTouchAction(driver);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.Context#open()
	 */
	@Override
	public void open() throws MalformedURLException
	{
		// HP ensure necessary capabilities got set already
		// such as device name

		driver = createDriver(remoteAddress);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.Context#open(java.lang.String)
	 */
	@Override
	public void open(String appIdOrFile) throws MalformedURLException
	{
		Util.mustNotNull("app id or file", appIdOrFile);

		//		setCapability(pkgOrBundleId(), appIdOrFile);
		setCapability(APP, new File(appIdOrFile).getAbsolutePath());

		open();

		if (!driver.isAppInstalled(appIdOrFile))
		{
		}
	}

	public void press(WebElement elem)
	{
		touch().press(elem).release().perform();
	}

	public byte[] pullFile(String fromWhere)
	{
		// HP validate args
		return driver.pullFile(fromWhere);
	}

	/**
	 * Remote address.
	 *
	 * @param remoteAddress
	 *            the remote address
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public T remoteAddress(String remoteAddress)
	{
		Util.mustNotNull("remote address", remoteAddress);
		this.remoteAddress = remoteAddress;
		return (T) this;
	}

	public void reset()
	{
		driver.resetApp();
	}

	public void runInBackground(int seconds)
	{
		driver.runAppInBackground(seconds);
	}

	public abstract void shake();

	@Override
	@SuppressWarnings("deprecation")
	public void swipe(int startx, int starty, int endx, int endy, int duration)
	{
		driver.swipe(startx, starty, endx, endy, duration);
	}

	public void tap(WebElement elem)
	{
		touch().tap(elem).release().perform();
	}

	/**
	 * To native.
	 *
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public T toNative()
	{
		driver.context(NATIVE_APP);
		return (T) this;
	}

	/**
	 * Touch.
	 *
	 * @return the touch action
	 */
	public TouchAction touch()
	{
		return new TouchAction(driver);
	}

	/**
	 * To web view.
	 *
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public T toWebView()
	{
		driver.context(WEBVIEW + 1);
		return (T) this;
	}

	/**
	 * To web view.
	 *
	 * @param index
	 *            the index
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public T toWebView(int index)
	{
		driver.context(WEBVIEW + index);
		return (T) this;
	}

	public abstract T UDID(String udid);

	public void uninstall(String bundleId)
	{
		driver.removeApp(bundleId);
	}

	public abstract void unlockDevice();

	/**
	 * Creates the driver.
	 *
	 * @param remoteAddress
	 *            the remote address
	 * @return the d
	 * @throws MalformedURLException
	 *             the malformed URL exception
	 */
	protected abstract D createDriver(String remoteAddress) throws MalformedURLException;

	/**
	 * @return
	 */
	protected abstract String pkgOrBundleId();

	/**
	 * Platform.
	 *
	 * @param platform
	 *            the platform
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	protected T platform(String platform)
	{
		setCapability(PLATFORM_NAME, platform);
		return (T) this;
	}
	@SuppressWarnings("unchecked")
	protected T version(String version)
	{
		setCapability(PLATFORM_VERSION, version);
		return (T) this;
	}
}