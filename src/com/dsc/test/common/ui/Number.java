/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

/**
 * A html5 added control for entering a floating point number.
 *
 * @Author alex
 * @CreateTime 22.02.2017 09:33:30
 * @Version 1.0
 * @Since 1.0
 */
public class Number extends ButtonBase
{
	public Number(Context<?, ?> context, String id)
	{
		this(context, context.findElemById(id));
	}

	/**
	 * @param browser
	 * @param wrapee
	 */
	public Number(Context<?, ?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}
}