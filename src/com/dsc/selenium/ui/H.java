/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

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
	public H(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	public abstract int level();
}
