/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ui.base;

import org.openqa.selenium.WebDriver;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.base.UIField;

import io.appium.java_client.MobileElement;

/**
 * @Author alex
 * @CreateTime 27.02.2017 09:42:17
 * @Version 1.0
 * @Since 1.0
 */
public class AppUIField extends UIField<MobileElement>
{

	/**
	 * @param context
	 * @param wrapee
	 */
	public AppUIField(Context<?, ? extends WebDriver> context, MobileElement wrapee)
	{
		super(context, wrapee);
	}

}
