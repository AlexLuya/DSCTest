package com.dsc.test.app.android;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class Top {
	public void clickControl(AndroidDriver driver, WebElement WebElement, String string) {
		
		// 获取控件开始位置的坐标轴
		Point start =  WebElement.getLocation();
		int startX = start.x;
		int startY = start.y;
		// 获取控件坐标轴差(二维数组)
		Dimension q = WebElement.getSize();
		int x = q.getWidth();
		int y = q.getHeight();
		// 计算出控件结束坐标
		int endX = x + startX;
		int endY = y + startY;

	
		switch (string) {
		// 左上 点击
		case "UPLEFT":
			driver.tap(1, startX + 10, startY + 10, 100);
			
			break;
		// 右上 点击
		case "UPRIGHT":
			driver.tap(1, endX - 10, startY + 10, 100);
		
			break;
//		// 左下 点击
//		case "LOWLEFT":
//			driver.tap(1, startX + 10, endY - 10, 100);
//			
//			break;
//		// 右下 点击
//		case "LOWRIGHT":
//			driver.tap(1, endX - 10, endY - 10, 100);
//			
//			break;
		// 中间 点击
		case "CENTRE":
			driver.tap(1, (endX + startX) / 2, (endY + startY) / 2, 100);
			
			break;
		}
	}
}
