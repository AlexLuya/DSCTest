/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.ui.widget.Widget;
import com.dsc.test.web.Browser;

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
