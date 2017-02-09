/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.TesteeHost;

public class Button extends ButtonBase
{
	public Button(TesteeHost browser, String containerId)
	{
		super(browser, containerId);
	}

	/**
	 * @param browser
	 * @param wrapee
	 */
	public Button(TesteeHost browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}
}