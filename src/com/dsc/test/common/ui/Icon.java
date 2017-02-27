/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.GeneralUIField;

/**
 * @Author alex
 * @CreateTime 24.02.2017 17:04:49
 * @Version 1.0
 * @Since 1.0
 */
public class Icon extends GeneralUIField
{

	/**
	 * @param context
	 * @param wrapee
	 */
	public Icon(Context<?, ?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	@Override
	public void click()
	{
		super.click();
	}
}
