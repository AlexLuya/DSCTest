/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.widget;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.UIObject;

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
	public FocusWidget(Context<? ,?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	/**
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
