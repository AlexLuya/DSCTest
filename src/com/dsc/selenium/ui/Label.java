/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

public class Label extends UIObject
{

	public Label(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dsc.selenium.ui.UIObject#ensureTextIs(java.lang.String)
	 */
	@Override
	public boolean ensureTextIs(String text)
	{
		return super.ensureTextIs(text);
	}

	/*
	 * make textIs() public
	 */
	@Override
	public String text()
	{
		return super.text();
	}
}
