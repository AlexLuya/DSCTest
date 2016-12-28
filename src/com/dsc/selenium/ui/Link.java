/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

public class Link extends UIObject
{

	public Link(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	public void clickMe()
	{
		wrapee.click();
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
