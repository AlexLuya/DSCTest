/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ui.base;

import org.openqa.selenium.WebElement;

import com.dsc.test.app.App;
import com.dsc.test.common.ui.base.GeneralUIField;
import com.dsc.test.common.ui.base.Widget;

/**
 * @Author alex
 * @CreateTime 16.02.2017 14:39:25
 * @Version 1.0
 * @Since 1.0
 */
public abstract class AppWidget extends Widget<App<?, ?>>
{
	/**
	 * @param context
	 * @param object
	 */
	public AppWidget(App<?, ?> context, GeneralUIField object)
	{
		super(context, object);
	}

	/**
	 * @param context
	 * @param id
	 */
	public AppWidget(App<?, ?> context, String id)
	{
		super(context, id);
	}

	/**
	 * @param context
	 * @param wrapee
	 */
	public AppWidget(App<?, ?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}
}
