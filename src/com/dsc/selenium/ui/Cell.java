/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

/**
 * @Author alex
 * @CreateTime Jan 23, 2015 11:07:10 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Cell extends UIObject
{
	/**
	 * @param browser
	 * @param wrapee
	 */
	public Cell(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}
}
