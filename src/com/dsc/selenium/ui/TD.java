/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

/**
 * @Author alex
 * @CreateTime Dec 23, 2016 1:15:44 PM
 * @Version 1.0
 * @Since 1.0
 */
public class TD extends UIObject
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	public TD(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	/*
	 * make ensureTextIs() public
	 */
	@Override
	public boolean ensureTextIs(String text)
	{
		return super.ensureTextIs(text);
	}

	@Override
	public String text()
	{
		return super.text();
	}
}
