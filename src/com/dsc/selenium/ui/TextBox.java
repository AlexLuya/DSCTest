/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

/**
 * The Class TextBox.
 */
public class TextBox extends AbstractValidable
{

	/**
	 * Instantiates a new text box.
	 *
	 * @param browser
	 *            the browser
	 * @param wrapee
	 *            the wrapee
	 */
	public TextBox(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	/**
	 * Clear.
	 *
	 * @return the text box
	 */
	public TextBox clear()
	{
		wrapee.clear();
		return this;
	}

	/*
	 *  make ensureTextIs() public
	 */
	@Override
	public boolean ensureTextIs(String text)
	{
		return super.ensureTextIs(text);
	}

	/**
	 * Input.
	 *
	 * @param text
	 *            the text
	 */
	public void input(String text)
	{
		clear();
		wrapee.sendKeys(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.selenium.ui.UIObject#text()
	 */
	@Override
	public String text()
	{
		return super.text();
	}
}