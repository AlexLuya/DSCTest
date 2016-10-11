/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;
import com.dsc.selenium.ui.gwt.Widget;

/**
 * @Author alex
 * @CreateTime Jan 16, 2015 6:42:36 PM
 * @Version 1.0
 * @Since 1.0
 */
public class ButtonBase extends Widget
{
	public ButtonBase(Browser browser, String containerId)
	{
		super(browser, containerId);
	}

	/**
	 * @param browser
	 * @param wrapee
	 */
	public ButtonBase(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}
}
