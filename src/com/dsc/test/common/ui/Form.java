/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.UIObject;

/**
 * @Author alex
 * @CreateTime Jan 23, 2015 11:07:10 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Form extends UIObject
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	public Form(Context<? ,?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

}
