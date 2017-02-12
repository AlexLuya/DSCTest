/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

public class Span extends UIObject
{
	public Span(Context<? ,?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	/*
	 * (non-Javadoc)
	 * @see com.dsc.test.common.ui.UIObject#ensureTextIs(int)
	 */
	@Override
	public boolean ensureTextIs(int toStringify)
	{
		return super.ensureTextIs(toStringify);
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
