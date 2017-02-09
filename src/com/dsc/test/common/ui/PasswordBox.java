package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.TesteeHost;

public class PasswordBox extends TextBox
{
	public PasswordBox(TesteeHost browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}
}
