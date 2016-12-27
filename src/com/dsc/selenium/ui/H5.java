/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

/**
 * @Author alex
 * @CreateTime Dec 27, 2016 4:35:28 PM
 * @Version 1.0
 * @Since 1.0
 */
public class H5 extends H
{
	/**
	 * @param browser
	 * @param wrapee
	 */
	public H5(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	/* (non-Javadoc)
	 * @see com.dsc.selenium.ui.H#level()
	 */
	@Override
	public int level()
	{
		return 5;
	}
}
