package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.UIObject;

public class Image extends UIObject
{
	public Image(Context<? ,?> context,WebElement wrapee)
	{
		super(context,wrapee);
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
