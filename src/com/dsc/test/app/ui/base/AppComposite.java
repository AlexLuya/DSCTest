/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ui.base;

import org.openqa.selenium.WebElement;

import com.dsc.test.app.App;
import com.dsc.test.common.ui.base.Composite;
import com.dsc.test.common.ui.base.UIObject;

/**
 * @Author alex
 * @CreateTime 16.02.2017 14:39:25
 * @Version 1.0
 * @Since 1.0
 */
public abstract class AppComposite extends Composite<App<?, ?>>
{
	/**
	 * @param context
	 * @param id
	 */
	public AppComposite(App<?, ?> context, String id)
	{
		super(context, id);
	}

	/**
	 * @param context
	 * @param object
	 */
	public AppComposite(App<?, ?> context, UIObject object)
	{
		super(context, object);
	}

	/**
	 * @param context
	 * @param wrapee
	 */
	public AppComposite(App<?, ?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}
}
