/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;

public class Anchor extends FocusWidget
{
	public Anchor(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.common.ui.UIObject#srcIs(java.lang.String)
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
