/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

// LP BLOG exception or boolean+log
public class GeneralUIField extends UIField<WebElement>
{

	/**
	 * @param context
	 * @param id
	 */
	public GeneralUIField(Context<?, ? extends WebDriver> context, String id)
	{
		super(context, id);
	}

	/**
	 * @param context
	 * @param id
	 */
	public GeneralUIField(Context<?, ? extends WebDriver> context, WebElement id)
	{
		super(context, id);
	}

}