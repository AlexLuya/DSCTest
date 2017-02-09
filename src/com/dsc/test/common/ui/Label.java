/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.TesteeHost;

public class Label extends UIObject
{

	public Label(TesteeHost browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dsc.test.common.ui.UIObject#ensureTextIs(java.lang.String)
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
