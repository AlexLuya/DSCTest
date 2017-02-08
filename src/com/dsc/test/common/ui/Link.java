/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;

public class Link extends UIObject
{

	public Link(Browser browser, WebElement wrapee)
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

	/*
	 * return href
	 */
	public String href()
	{
		return attr("href");
	}

	/*
	 * make ensureTextIs() public
	 */
	@Override
	public String text()
	{
		return super.text();
	}
}
