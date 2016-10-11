package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;



public class Link extends UIObject{

	public Link(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}

	public void clickMe() {
		wrapee.click();
	}
}

