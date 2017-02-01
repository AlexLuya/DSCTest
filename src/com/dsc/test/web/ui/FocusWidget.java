/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;

/**
 * @Author alex
 * @CreateTime Jan 5, 2015 11:14:38 AM
 * @Version 1.0
 * @Since 1.0
 */
public class FocusWidget extends UIObject
{
	/**
	 * @param wrapee
	 */
	public FocusWidget(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.dsc.test.web.ui.UIObject#ensureTextIs(java.lang.String)
	 */
	@Override
	public boolean ensureTextIs(String text)
	{
		return super.ensureTextIs(text);
	}

	/**
	 * used to exposure text() method
	 *
	 * @return
	 */
	@Override
	public String text()
	{
		return super.text();
	}
}
