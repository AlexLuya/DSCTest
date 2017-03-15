/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.HasText;
import com.dsc.test.common.ui.base.ValidableBase;

/**
 * The Class TextBox.
 */
public class TextBox extends ValidableBase implements HasText
{

	/**
	 * Instantiates a new text box.
	 *
	 * @param browser
	 *            the browser
	 * @param wrapee
	 *            the wrapee
	 */
	public TextBox(Context<?, ?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	/**
	 * Clear.
	 *
	 * @return the text box
	 */
	@Override
	public void clear()
	{
		super.clear();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.HasText#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String text)
	{
		return super.contains(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.HasText#endsWith(java.lang.String)
	 */
	@Override
	public boolean endsWith(String text)
	{
		return super.endsWith(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.UIObject#ensureTextIs(java.lang.String)
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
	@Override
	public void input(String text)
	{
		super.input(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.HasText#partOf(java.lang.String)
	 */
	@Override
	public boolean partOf(String text)
	{
		return super.partOf(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.base.HasText#startsWith(java.lang.String)
	 */
	@Override
	public boolean startsWith(String text)
	{
		return super.startsWith(text);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.common.ui.UIObject#text()
	 */
	@Override
	public String text()
	{
		return super.text();
	}
}