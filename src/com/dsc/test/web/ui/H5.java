/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;

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
	 * @see com.dsc.test.web.ui.H#level()
	 */
	@Override
	public int level()
	{
		return 5;
	}
}
