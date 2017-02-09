/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
**/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.TesteeHost;

/**
 * @Author alex
 * @CreateTime Dec 30, 2014 7:52:25 PM
 * @Version 1.0
 * @Since 1.0
 */
public class HTMLPanel extends UIObject
{

	/**
	 * @param wrapee
	 */
	public HTMLPanel(TesteeHost browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}

}
