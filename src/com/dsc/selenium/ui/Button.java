/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

public class Button extends ButtonBase
{
	public Button(Browser browser, String containerId)
	{
		super(browser, containerId);
	}

	/**
	 * @param browser
	 * @param wrapee
	 */
	public Button(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	@Override
	public void click()
	{
		wrapee.click();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.selenium.ui.UIObject#ensureTextIs(int)
	 */
	@Override
	public boolean ensureTextIs(String text)
	{
		return super.ensureTextIs(text);
	}

	public boolean isDisabled()
	{
		return !isEnabled();
	}

	/**
	 * @return
	 */
	public boolean isEnabled()
	{
		return wrapee.isEnabled();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.selenium.ui.UIObject#text()
	 */
	@Override
	protected String text()
	{
		return super.text();
	}
}