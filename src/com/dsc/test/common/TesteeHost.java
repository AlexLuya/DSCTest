/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common;

import static com.dsc.util.Log.info;
import static com.dsc.util.Log.warn;
import static com.dsc.util.Util.wrap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dsc.test.common.ui.UIObject;
import com.dsc.test.common.TesteeHost;
import com.dsc.util.Util;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.cropper.indent.BlurFilter;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * @Author alex
 * @CreateTime Feb 8, 2017 4:30:02 PM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class TesteeHost
{
	private static final int	DEFAULT_WAIT_SECONDS		= 3;
	private static int			REAL_WAIT_SECONDS			= DEFAULT_WAIT_SECONDS;
	private static final String	UTF_8						= "UTF-8";
	/**
	 * @return
	 */
	private static int defaultWaitSeconds()
	{
		return REAL_WAIT_SECONDS;
	}



	private WebDriver	driver;

	private String		previousWindowHandler;



	public Actions actions()
	{
		return new Actions(driver);
	}

	public Alert alter()
	{
		try
		{
			return driver.switchTo().alert();
		} catch (NoAlertPresentException e)
		{
			return null;
		}
	}

	public void close()
	{
		driver.quit();
	}

	public void closeCurrentWindow()
	{
		driver.close();
	}

	public void deleteAllCookies()
	{
		manage().deleteAllCookies();
	}

	public void deleteCookie(String key)
	{
		manage().deleteCookieNamed(key);
	}

	public boolean ensureNotPresented(String id)
	{
		if (doFindElemById(id) != null)
		{
			throw new IllegalStateException(String.format("'%s' shouldn't be presented,but it is", id));
		}

		return true;
	}

	/**
	 * @param script
	 * @param args
	 */
	public void executeScript(String script, Object... args)
	{
		jsExecutor().executeScript(script, args);
	}

	public WebElement findDivByText(String text)
	{

		return findDivByText("//div[contains(text(),'" + text + "')]");
	}

	public WebElement findElemById(String id)
	{
		if (id == null || id == "")
		{
			throw new IllegalArgumentException("id mustn't be null");
		}

		try
		{
			waitUntil(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		} catch (TimeoutException e)
		{
			throw new NoSuchElementException("Can't find the element with ----------------id:---------------" + id, e);
		}

		return doFindElemById(id);
	}

	public WebElement findElemByLinkText(String text)
	{
		if (text == null || text == "")
		{
			throw new IllegalArgumentException("linkText mustn't be null");
		}

		try
		{
			waitUntil(ExpectedConditions.visibilityOfElementLocated(By.linkText(text)));
		} catch (TimeoutException e)
		{
			throw new NoSuchElementException("Can't find the element with ----------------linkText:---------------" + text, e);
		}

		return driver.findElement(By.linkText(text));
	}

	public WebElement findElemByTag(String tag)
	{
		if (tag == null || tag == "")
		{
			throw new IllegalArgumentException("tag name mustn't be null");
		}

		try
		{
			waitUntil(ExpectedConditions.visibilityOfElementLocated(By.tagName(tag)));
		} catch (TimeoutException e)
		{
			throw new NoSuchElementException("Can't find the element with ----------------tagName:---------------" + tag, e);
		}

		driver.findElement(By.tagName(tag)).getSize();

		return driver.findElement(By.tagName(tag));
	}

	public WebElement findElemByXpath(String xpath)
	{
		if (xpath == null || xpath == "")
		{
			throw new IllegalArgumentException("xpath mustn't be null");
		}

		try
		{
			waitUntil(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (TimeoutException e)
		{
			throw new NoSuchElementException("Can't find the element with ----------------xpath:---------------" + xpath, e);
		}

		return driver.findElement(By.xpath(xpath));
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

	/**
	 * @param id
	 * @return
	 */
	public UIObject getUIObjectById(String id)
	{
		return new UIObject(this, findElemById(id));
	}

	public void hitEnterKey()
	{
		actions().sendKeys(Keys.ENTER).perform();
	}

	/**
	 * @param target
	 * @deprecated use {@link #moveToElement(WebElement)} instead
	 */
	@Deprecated
	public WebElement mouseMoveTo(WebElement target)
	{
		return moveToElement(target);
	}

	/**
	 * @param target
	 * @param x
	 * @param y
	 * @deprecated use {@link #moveToElement(WebElement, int , int )} instead
	 */
	@Deprecated
	public void mouseMoveTo(WebElement target, int x, int y)
	{
		moveToElement(target, x, y);
	}

	/**
	 * @param target
	 */
	public WebElement moveToElement(WebElement target)
	{
		new Actions(driver).moveToElement(target, 0, 0).perform();

		return target;
	}

	/**
	 * @param target
	 * @param x
	 * @param y
	 */
	public void moveToElement(WebElement target, int x, int y)
	{
		new Actions(driver).moveToElement(target, x, y).perform();
	}

	public Navigation navigation()
	{
		return driver.navigate();
	}

	public void open(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}

	public void refresh()
	{
		driver.navigate().refresh();
	}

	public void resetWaitSeconds()
	{
		REAL_WAIT_SECONDS = DEFAULT_WAIT_SECONDS;
	}

	// a method to obtain screenshots
	public File screenshotOf(WebElement elem)
	{
		if (driver instanceof ChromeDriver)
		{
			return doTakeScreenshotByAShot(elem);
		}

		return takeScreenshotOf(elem);

	}

	public void setCookie(String key, String value)
	{
		setCookie(key, value, "/");
	}

	public void setCookie(String key, String value, String path)
	{
		manage().addCookie(new Cookie(key, value, path));
	}

	public void setWaitSeconds(int defaultWaitSeconds)
	{
		REAL_WAIT_SECONDS = defaultWaitSeconds;
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

	public void switchToWindow(String nameOrHandler)
	{
		// this will switch to expected window/tab but visually,it may won't
		// due to window is opened in tab
		driver.switchTo().window(nameOrHandler);

		// so use a trick(show an alter,and click [OK]) to ensure focused
		// visually
		// but if 'alter' existed
		if (null != alter())
		{
			// trick won't work,user must do it manually
			warn("alter() existed,and it will shade the trick---alter('ensure focused visually').accept()---,please do it manually");
			return;
		}

		// else use this trick
		executeScript("alert('ensure focused visually')");
		alter().accept();
	}

	public File takeScreenshot()
	{
		// take a screenshot
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	public <T> T waitUntil(ExpectedCondition<T> isTrue) throws TimeoutException
	{
		return new WebDriverWait(driver, TesteeHost.defaultWaitSeconds()).until(isTrue);
	}

	public Window window()
	{
		return driver.manage().window();
	}

	public String[] windowHandles()
	{
		return driver.getWindowHandles().toArray(new String[driver.getWindowHandles().size()]);
	}

	Options manage()
	{
		return driver.manage();
	}

	/**
	 * @param id
	 * @return
	 */
	private WebElement doFindElemById(String id)
	{
		try
		{
			return driver.findElement(By.id(id));
		} catch (NoSuchElementException e)
		{
			info("element '%s' not presented in page: '%s'", id, driver.getCurrentUrl());
			return null;
		}
	}

	/**
	 * @param elem
	 * @return
	 */
	private File doTakeScreenshotByAShot(WebElement elem)
	{
		try
		{
			Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider())
					.imageCropper(new IndentCropper().addIndentFilter(new BlurFilter()))
					.shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver, elem);

			File tempFile = File.createTempFile(screenshotUrl(), ".png");

			ImageIO.write(screenshot.getImage(), "png", tempFile);

			tempFile.deleteOnExit();

			return tempFile;
		} catch (Exception e)
		{
			throw new RuntimeException(wrap("take screenshot failed"), e.getCause());
		}
	}

	/**
	 * @return
	 */
	private JavascriptExecutor jsExecutor()
	{
		return (JavascriptExecutor) driver;
	}

	/**
	 * @return
	 */
	private String screenshotUrl()
	{
		return "screenshot-" + new Date().getTime() + new Random().nextLong();
	}

	private File takeScreenshotOf(WebElement elem)
	{
		File screenshot = takeScreenshot();

		// Get the location of element on the page
		Point point = elem.getLocation();

		// Get width and height of the element
		int elemWidth = elem.getSize().getWidth();
		int elemHeight = elem.getSize().getHeight();

		try
		{
			BufferedImage fullImg = ImageIO.read(screenshot);
			// Crop the entire page screenshot to get only element screenshot
			BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elemWidth, elemHeight);
			ImageIO.write(eleScreenshot, "png", screenshot);
		} catch (IOException e)
		{
			throw new RuntimeException(wrap("take screenshot IO failed"), e.getCause());
		}

		return screenshot;
	}
}
