/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.ui.base;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.ui.base.GeneralUIField;
import com.dsc.test.common.ui.base.Widget;
import com.dsc.test.web.Browser;

/**
 * @Author alex
 * @CreateTime Jan 5, 2015 11:15:04 AM
 * @Version 1.0
 * @Since 1.0
 */
public class WebWidget extends Widget<Browser>
{
	public WebWidget(Browser browser, String containerId)
	{
		this(browser, browser.findElemById(containerId));
	}

	public WebWidget(Browser browser, GeneralUIField wrapee){
		this(browser,wrapee.element());
	}

	public WebWidget(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}
}
