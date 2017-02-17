/**
 * gat * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All
 * rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.logging.Level;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.safari.SafariDriver;

import com.dsc.test.common.Context;
import com.dsc.test.common.pagefactory.UIObjectDecorator;
import com.dsc.util.Util;

public class Browser extends Context<Browser, WebDriver>
{
	private static final String	CHROME_DRIVER_DIR			= "DSCTest/chromedrivers";

	private static final String	UTF_8						= "UTF-8";
	// NP get from configuration file
	private static final String	WHERE_CHROME_DRIVER_IN_LIN	= "/usr/local/bin/chromedriver.lin";
	private static final String	WHERE_CHROME_DRIVER_IN_MAC	= "/usr/local/bin/chromedriver.mac";

	private static final String	WHERE_CHROME_DRIVER_IN_WIN	= "C:\\Program Files\\chromedriver.exe";

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
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		return new Browser(new ChromeDriver(cap(cap)), cap);
	}

	public static Browser firfox()
	{
		// System.setProperty("webdriver.firefox.driver", WHERE_FIREFOX_DRIVER);
		// System.setProperty("webdriver.firefox.bin", WHERE_FIREFOX_BIN);
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setCapability("marionette", true);

		return new Browser(new FirefoxDriver(cap(cap)), cap);
	}

	// private static final String WHERE_FIREFOX_BIN = "/usr/bin/firefox";
	// private static final String WHERE_FIREFOX_DRIVER =
	// "/usr/local/bin/geckodriver";

	public static Browser ie()
	{
		return new Browser(new InternetExplorerDriver(), DesiredCapabilities.internetExplorer());
	}

	public static Browser safari()
	{
		return new Browser(new SafariDriver(), DesiredCapabilities.safari());
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

	private String previousWindowHandler;

	public Browser(WebDriver driver, DesiredCapabilities cap)
	{
		super(cap);
		this.driver = driver;
	}

	public void closeCurrentWindow()
	{
		driver.close();
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.common.Context#decorator(org.openqa.selenium.SearchContext)
	 */
	@Override
	public UIObjectDecorator decorator(SearchContext searchCxt)
	{
		return new WebUIObjectDecorator(this, searchCxt);
	}

	public void deleteAllCookies()
	{
		manage().deleteAllCookies();
	}

	public void deleteCookie(String key)
	{
		manage().deleteCookieNamed(key);
	}

	public String getCookie(String key)
	{
		Util.mustNotNullOrEmpty(key, "cookie key");

		for (Cookie ck : driver.manage().getCookies())
		{
			if (ck.getName().equals(key))
			{
				try
				{
					return URLDecoder.decode(ck.getValue(), UTF_8);
				} catch (UnsupportedEncodingException e)
				{
					throw new IllegalStateException(
							String.format("'%s''s cookie value:'%s' can't be decoded as %s", key, ck.getValue(), UTF_8), e);
				}
			}
		}

		return null;
	}

	public String getCurrentUrl()
	{
		return driver.getCurrentUrl();
	}

	public Navigation navigation()
	{
		return driver.navigate();
	}

	@Override
	public void open()
	{
		open("about:blank");
	}

	@Override
	public void open(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}

	public void refresh()
	{
		driver.navigate().refresh();
	}

	public void scrollTo(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<>();
		scrollObject.put("direction", "down");
		scrollObject.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: scroll", scrollObject);
	}

	public void setCookie(String key, String value)
	{
		setCookie(key, value, "/");
	}

	public void setCookie(String key, String value, String path)
	{
		manage().addCookie(new Cookie(key, value, path));
	}

	public void switchBackToPreviousWindow()
	{
		switchToWindow(previousWindowHandler);
		previousWindowHandler = null;
	}

	public void switchToPopupWindow()
	{
		// store current window for switching back
		previousWindowHandler = driver.getWindowHandle();
		// switch to popoup window(last is latest opened under webdriver
		// mechanism)
		switchToWindow(windowHandles().length - 1);
	}

	public void switchToWindow(int index)
	{
		switchToWindow(windowHandles()[index]);
	}

	public Window window()
	{
		return driver.manage().window();
	}

	public String[] windowHandles()
	{
		return driver.getWindowHandles().toArray(new String[driver.getWindowHandles().size()]);
	}
}