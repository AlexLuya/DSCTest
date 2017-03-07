/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

/**
 * A html5 added field for editing a URL. The input value is validated to
 * contain either the empty string or a valid absolute URL before submitting.
 * You can use attributes such as pattern and maxlength to restrict values
 * entered in the control. The :valid and :invalid CSS pseudo-classes are
 * applied as appropriate.
 *
 * @Author alex
 * @CreateTime 22.02.2017 09:33:30
 * @Version 1.0
 * @Since 1.0
 */
public class Url extends ButtonBase
{
	public Url(Context<?, ?> context, String id)
	{
		this(context, context.findElemById(id));
	}

	/**
	 * @param browser
	 * @param wrapee
	 */
	public Url(Context<?, ?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}
}