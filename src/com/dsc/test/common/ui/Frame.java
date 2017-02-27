/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.GeneralUIField;


/**
 * @Author alex
 * @CreateTime Dec 30, 2014 10:41:11 PM
 * @Version 1.0
 * @Since 1.0
 */
public class Frame extends GeneralUIField
{
	/**
	 * @param wrapee
	 */
	public Frame(Context<? ,?> context,WebElement wrapee)
	{
		super(context,wrapee);
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.common.ui.UIObject#ensureSrcEndWith(java.lang.String)
	 */
	@Override
	public boolean srcEndsWith(String src)
	{
		return super.srcEndsWith(src);
	}
}
