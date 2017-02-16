/**
 * Copyright (c) (2010-2013),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
**/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

public class PasswordBox extends TextBox
{
	public PasswordBox(Context<? ,?> context,WebElement wrapee)
	{
		super(context,wrapee);
	}
}
