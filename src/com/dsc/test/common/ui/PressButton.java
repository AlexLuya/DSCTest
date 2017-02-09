package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.TesteeHost;



public class PressButton extends UIObject
{

	public PressButton(TesteeHost browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}
	public String getText()
	{
		return wrapee.getText();
	}


}
