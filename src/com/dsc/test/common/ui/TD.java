/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.GeneralUIField;

/**
 * @Author alex
 * @CreateTime Dec 23, 2016 1:15:44 PM
 * @Version 1.0
 * @Since 1.0
 */
public class TD extends GeneralUIField
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	public TD(Context<? ,?> context, WebElement wrapee)
	{
		super(context, wrapee);
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
	public String text()
	{
		return super.text();
	}
}
