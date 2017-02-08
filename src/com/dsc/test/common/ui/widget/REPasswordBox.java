/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
**/
package com.dsc.test.common.ui.widget;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;

/**
 * @Author alex
 * @CreateTime Jan 17, 2015 10:35:45 PM
 * @Version 1.0
 * @Since 1.0
 */
public class REPasswordBox extends RETextBox
{

	/**
	 * @param el
	 */
	public REPasswordBox(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}

}
