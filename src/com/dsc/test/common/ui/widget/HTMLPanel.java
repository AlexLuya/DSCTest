/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.Composite;

/**
 * @Author alex
 * @CreateTime Dec 30, 2014 7:52:25 PM
 * @Version 1.0
 * @Since 1.0
 */
public abstract class HTMLPanel extends Composite<Context<? ,? extends WebDriver>>
{

	/**
	 * @param wrapee
	 */
	public HTMLPanel(Context<? ,?> context,WebElement wrapee)
	{
		super(context,wrapee);
	}

}
