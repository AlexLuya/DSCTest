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
	 * @param wrapeeId
	 */
	public GeneralUIField(Context<?, ? extends WebDriver> context, String wrapeeId)
	{
		super(context, wrapeeId);
	}

	/**
	 * @param context
	 * @param wrappee
	 */
	public GeneralUIField(Context<?, ? extends WebDriver> context, WebElement wrappee)
	{
		super(context, wrappee);
	}

}