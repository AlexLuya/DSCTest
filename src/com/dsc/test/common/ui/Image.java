package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.TesteeHost;

public class Image extends UIObject
{
	public Image(TesteeHost browser,WebElement wrapee)
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
