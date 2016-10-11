/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
**/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;

/**
 * @Author alex
 * @CreateTime Dec 30, 2014 8:43:06 PM
 * @Version 1.0
 * @Since 1.0
 */
public class CheckBox extends UIObject
{

	/**
	 * @param wrapee
	 */
	public CheckBox(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}

	/**
	 * @param auto
	 */
	public void setValue(boolean checked)
	{
		if(wrapee.isSelected()!=checked)
		{
			wrapee.click();
		}
	}

}
