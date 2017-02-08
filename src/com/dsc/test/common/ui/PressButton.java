package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;



public class PressButton extends UIObject
{

	public PressButton(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}
	public String getText()
	{
		return wrapee.getText();
	}


}
