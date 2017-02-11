package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;



public class PressButton extends UIObject
{

	public PressButton(Context context,WebElement wrapee)
	{
		super(context,wrapee);
	}
	public String getText()
	{
		return wrapee.getText();
	}


}
