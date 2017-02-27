/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.app.ui;

import org.openqa.selenium.WebDriver;

import com.dsc.test.app.ui.base.AppUIField;
import com.dsc.test.common.Context;

import io.appium.java_client.MobileElement;

/**
 * @Author alex
 * @CreateTime 25.02.2017 22:25:46
 * @Version 1.0
 * @Since 1.0
 */
public class PanelControl extends AppUIField
{

	/**
	 * @param context
	 * @param wrapee
	 */
	public PanelControl(Context<?, ? extends WebDriver> context, MobileElement wrapee)
	{
		super(context, wrapee);
	}

}
