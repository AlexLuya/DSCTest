/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.widget;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.Label;

/**
 * @Author alex
 * @CreateTime Jan 18, 2015 9:27:47 PM
 * @Version 1.0
 * @Since 1.0
 */
public class MouseSensitiveLabel extends Label
{
	/**
	 * @param el
	 */
	public MouseSensitiveLabel(Context<? ,?> context,WebElement wrapee)
	{
		super(context,wrapee);
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.common.ui.Label#textIs(java.lang.String)
	 */
	@Override
	public String text()
	{
		return super.text();
	}
}
