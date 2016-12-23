/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;
import com.dsc.selenium.ui.gwt.Widget;

/**
 * @Author alex
 * @CreateTime Dec 23, 2016 1:18:18 PM
 * @Version 1.0
 * @Since 1.0
 */
public class TR extends Widget
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	public TR(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}

	public TD cell(int index){
		return new TD(browser, childElem(index));
	}
}
