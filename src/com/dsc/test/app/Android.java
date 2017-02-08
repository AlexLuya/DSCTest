/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.dsc.util.Util;

import io.appium.java_client.android.AndroidDriver;

/**
 * @Author alex
 * @CreateTime Feb 7, 2017 6:17:47 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Android
{
	public static  Android run(String apkPath) throws MalformedURLException{
		return new Android(apkPath);
	}

	DesiredCapabilities cap = new DesiredCapabilities();

	private final AndroidDriver driver;

	private Android(String apkPath) throws MalformedURLException{
		Util.mustNotNull("apk path", apkPath);

		cap.setCapability("app", apkPath);
		cap.setCapability("platformName", "Android");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);// 把以上配置传到appium服务端并连接手机

		// 隐式等待
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
