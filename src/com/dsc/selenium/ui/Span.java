/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

public class Span extends UIObject
{
	public Span(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	/*
	 *  make ensureTextIs() public
	 */
	@Override
	public boolean ensureTextIs(int toStringify)
	{
		return super.ensureTextIs(toStringify);
	}

	/*
	 *  make ensureTextIs() public
	 */
	@Override
	public boolean ensureTextIs(String text)
	{
		return super.ensureTextIs(text);
	}

	@Override
	public boolean ensureTextIsPartOf(String whole)
	{
		return super.ensureTextIsPartOf(whole);
	}

	@Override
	public boolean ensureTextStartsWith(String expected)
	{
		return super.ensureTextStartsWith(expected);
	}

	@Override
	public String text()
	{
		return wrapee.getText();
	}
}
