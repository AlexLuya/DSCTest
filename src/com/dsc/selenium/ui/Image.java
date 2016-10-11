package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

public class Image extends UIObject
{
	public Image(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}

	/**
	 * @param image
	 * @return
	 */
	@Override
	public void ensureSrcEndWith(String image)
	{
		 super.ensureSrcEndWith(image);
	}

	@Override
	public String src()
	{
		return super.src();
	}
}
