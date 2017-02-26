/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.UIObject;

/**
 * @Author alex
 * @CreateTime 25.02.2017 22:32:27
 * @Version 1.0
 * @Since 1.0
 */
public class StatusBar extends UIObject
{

	/**
	 * @param context
	 * @param wrapee
	 */
	public StatusBar(Context<?, ? extends WebDriver> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

}
