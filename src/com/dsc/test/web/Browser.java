/**
 * gat * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All
 * rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web;

import java.io.File;
import java.util.logging.Level;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.dsc.test.common.TesteeHost;

public class Browser extends TesteeHost
{
	private static final String	CHROME_DRIVER_DIR			= "DSCTest/chromedrivers";

	// NP get from configuration file
	private static final String	WHERE_CHROME_DRIVER_IN_LIN	= "/usr/local/bin/chromedriver.lin";
	private static final String	WHERE_CHROME_DRIVER_IN_MAC	= "/usr/local/bin/chromedriver.mac";
	private static final String	WHERE_CHROME_DRIVER_IN_WIN	= "C:\\Program Files\\chromedriver.exe";

	// private static final String WHERE_FIREFOX_BIN = "/usr/bin/firefox";
	// private static final String WHERE_FIREFOX_DRIVER =
	// "/usr/local/bin/geckodriver";

	public static DesiredCapabilities cap(DesiredCapabilities caps)
	{
		LoggingPreferences logPrefs = new LoggingPreferences();

		logPrefs.enable(LogType.BROWSER, Level.ALL);
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

		return caps;
	}

	public static Browser chrome()
	{
		// if driver file not exist under dedicated dir
		if (!new File(whereChromeDriver()).exists())
		{
			throw new IllegalStateException(String.format("please copy OS corresponded chrome driver from %s to %s",
					CHROME_DRIVER_DIR, whereChromeDriver()));
		}

		System.setProperty("webdriver.chrome.driver", whereChromeDriver());

		// disable popup blocking
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("disable-popup-blocking");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		return new Browser(new ChromeDriver(cap(capabilities)));
	}

	public static Browser firfox()
	{
		// System.setProperty("webdriver.firefox.driver", WHERE_FIREFOX_DRIVER);
		// System.setProperty("webdriver.firefox.bin", WHERE_FIREFOX_BIN);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);

		return new Browser(new FirefoxDriver(cap(capabilities)));
	}

	public static Browser ie()
	{
		return new Browser(new InternetExplorerDriver());
	}

	public static Browser safari()
	{
		return new Browser(new SafariDriver());
	}

	/**
	 *
	 */
	private static String whereChromeDriver()
	{

		if (SystemUtils.IS_OS_WINDOWS)
		{
			return WHERE_CHROME_DRIVER_IN_WIN;
		}

		if (SystemUtils.IS_OS_LINUX)
		{
			return WHERE_CHROME_DRIVER_IN_LIN;
		}

		if (SystemUtils.IS_OS_MAC)
		{
			return WHERE_CHROME_DRIVER_IN_MAC;
		}

		throw new IllegalStateException(
				SystemUtils.OS_NAME + " NOT SUPPORTED due to no corresponded chrome driver provided by vendor");
	}

	private Browser(WebDriver driver)
	{
		this.driver = driver;
	}

}