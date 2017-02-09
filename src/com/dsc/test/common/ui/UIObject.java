/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.TesteeHost;

// LP BLOG exception or boolean+log
public class UIObject extends AbstractUIObject<TesteeHost>
{
	/**
	 * @param browser
	 * @param wrapee
	 */
	public UIObject(TesteeHost browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}
}