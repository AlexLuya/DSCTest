/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;

/**
 * @Author alex
 * @CreateTime Dec 27, 2016 4:35:28 PM
 * @Version 1.0
 * @Since 1.0
 */
public class H6 extends H
{
	/**
	 * @param browser
	 * @param wrapee
	 */
	public H6(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.common.ui.H#level()
	 */
	@Override
	public int level()
	{
		return 6;
	}
}
