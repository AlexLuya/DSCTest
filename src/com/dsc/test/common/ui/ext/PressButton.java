/**
 * Copyright (c) (2010-2013),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
**/
package com.dsc.test.common.ui.ext;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.GeneralUIField;

public class PressButton extends GeneralUIField
{

	public PressButton(Context<? ,?> context,WebElement wrapee)
	{
		super(context,wrapee);
	}
	public String getText()
	{
		return wrapee.getText();
	}
}
