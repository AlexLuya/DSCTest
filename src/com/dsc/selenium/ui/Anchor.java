/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

public class Anchor extends FocusWidget
{
	public Anchor(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}

	/* (non-Javadoc)
	 * @see com.dsc.selenium.ui.UIObject#srcIs(java.lang.String)
	 */
	@Override
	public void ensureSrcEndWith(String image)
	{
		super.ensureSrcEndWith(image);
	}

	/*
	 * return href
	 */
	public String href()
	{
		return attr("href");
	}
}
