/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.h5;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

/**
 * @Author alex
 * @CreateTime Feb 12, 2017 12:16:11 AM
 * @Version 1.0
 * @Since 1.0
 */
public class H5TestExample
{
	public static void main(String[] args) throws Exception {
		AndroidDriver driver;

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("automationName", "Appium");// appium做自动化
		// cap.setCapability("app", "C:\\software\\jrtt.apk");//安装apk
		cap.setCapability("browserName", "Chrome");// 设置HTML5的自动化，打开谷歌浏览器
		cap.setCapability("deviceName", "OPPO R7 Plusm");// 设备名称
		cap.setCapability("platformName", "Android"); // 安卓自动化还是IOS自动化
		cap.setCapability("platformVersion", "5.1.1"); // 安卓操作系统版本
		cap.setCapability("udid", "dba18022"); // 设备的udid (adb devices 查看到的)
		// cap.setCapability("appPackage", "com.sky.jisuanji");//被测app的包名
		// cap.setCapability("appActivity",
		// ".JisuanjizixieActivity");//被测app的入口Activity名称
		cap.setCapability("safariInitialUrl", "https://www.baidu.com");
		cap.setCapability("unicodeKeyboard", "True"); // 支持中文输入
		cap.setCapability("resetKeyboard", "True"); // 支持中文输入，必须两条都配置
		cap.setCapability("noSign", "True"); // 不重新签名apk
		cap.setCapability("newCommandTimeout", "10"); // 没有新命令，appium30秒退出

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);// 把以上配置传到appium服务端并连接手机
		// 隐式等待
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.baidu.com");
		driver.findElement(By.id("index-kw")).sendKeys("软件测试");
		driver.findElement(By.id("index-bn")).click();
	}
}
