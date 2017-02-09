/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.TesteeHost;

/**
 * @Author alex
 * @CreateTime Dec 27, 2016 4:35:08 PM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class H extends Label
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	public H(TesteeHost browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	public abstract int level();
}
