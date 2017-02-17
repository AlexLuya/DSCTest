/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.android;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

/**
 * @Author alex
 * @CreateTime Feb 12, 2017 12:12:05 AM
 * @Version 1.0
 * @Since 1.0
 */
public class AndroidTestExampleSusan
{
	public static void main(String[] args) throws Exception {
		AndroidDriver<RemoteWebElement> driver;

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("automationName", "Appium");// appium做自动化
		// cap.setCapability("app", "C:\\software\\jrtt.apk");//安装apk
		// cap.setCapability("browserName", "Chrome");//设置HTML5的自动化，打开谷歌浏览器
		// cap.setCapability("deviceName", "H60-L03");// 设备名称
		cap.setCapability("deviceName", "Galaxy S4");// 设备名称
		cap.setCapability("platformName", "Android"); // 安卓自动化还是IOS自动化
		cap.setCapability("platformVersion", "5.0.1"); // 安卓操作系统版本
		// cap.setCapability("platformVersion", "4.4.2"); // 安卓操作系统版本
		// cap.setCapability("udid", "6&33b008c1&0&0001"); // 设备的udid (adb devices 查看到的)
		cap.setCapability("appPackage", "com.taobao.taobao");// 被测app的包名
		cap.setCapability("appActivity", "com.taobao.tao.homepage.MainActivity3");// 被测app的入口Activity名称
		// cap.setCapability("safariInitialUrl","https://www.baidu.com");
		cap.setCapability("unicodeKeyboard", "True"); // 支持中文输入
		cap.setCapability("resetKeyboard", "True"); // 支持中文输入，必须两条都配置
		cap.setCapability("noSign", "True"); // 不重新签名apk
		cap.setCapability("newCommandTimeout", "30"); // 没有新命令，appium30秒退出

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);// 把以上配置传到appium服务端并连接手机
		// 隐式等待
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 登录账户
		driver.findElementByName("我的淘宝").click();
		driver.findElementByName("其他账户登录").click();
		driver.findElementByAccessibilityId("账户名输入框").sendKeys("18163914190");

		driver.findElementByAccessibilityId("密码输入框").sendKeys("hsz130521");
		driver.findElementByName("登录").click();

		// 任意选购一件商品
		driver.findElementByName("首页").click();
		driver.findElementByXPath("//android.widget.EditText").click();
		driver.findElementById("com.taobao.taobao:id/searchEdit").sendKeys("箱包");
		driver.findElementByName("搜索").click();

		driver.findElementByName("销量优先").click();

		driver.findElementByXPath("//android.widget.ImageView[@index='0']").click();

		driver.findElementByName("加入购物车").click();
		driver.findElementByName("20寸").click();
		driver.findElementByName("镜面玫瑰金杯架").click();
		driver.findElementByName("确定").click();

		// 购物车下单
		driver.findElementByXPath("//android.widget.TextView[@text='ꁊ']").click();//点击小购物车

		driver.findElementByAccessibilityId("勾选宝贝").click();
		driver.findElementByAccessibilityId("结算").click();
		// 提交订单
		driver.findElementByAccessibilityId("提交订单").click();
		// 支付宝支付
		driver.findElementByAccessibilityId("confirm").click();

		// 输入错误密码

		// driver.findElementById("com.taobao.taobao:id/row1_frame_num").click();
		// driver.findElementById("com.taobao.taobao:id/row2_frame_num").click();
		//driver.findElementByClassName("android.widget.LinearLayout");

		// 输入密码方法2：封装点击 根据元素来定位
		// 第一排数字 对象
		WebElement num2 = driver.findElementByXPath("//android.widget.LinearLayout[@index='2']");
		Top top_num = new Top();
		// 数字1：第一排左边（左上7）
		top_num.clickControl(driver, num2, "UPLEFT");
		Thread.sleep(2000);
		// 数字2：第一排中间8
		top_num.clickControl(driver, num2, "CENTRE");
		Thread.sleep(2000);
		// 数字2：第一排右边（右上）9
		top_num.clickControl(driver, num2, "UPRIGHT");
		Thread.sleep(2000);


		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.findElementByClassName("android.widget.FrameLayout").click();
		driver.pressKeyCode(AndroidKeyCode.BACK);
		// 退出登录
		driver.findElementByName("我的淘宝").click();
		driver.findElementByAccessibilityId("设置").click();
		driver.findElementByName("退出当前账户").click();

	}
}
