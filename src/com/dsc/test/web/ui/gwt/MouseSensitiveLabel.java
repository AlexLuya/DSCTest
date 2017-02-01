/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.ui.gwt;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;
import com.dsc.test.web.ui.Label;

/**
 * @Author alex
 * @CreateTime Jan 18, 2015 9:27:47 PM
 * @Version 1.0
 * @Since 1.0
 */
public class MouseSensitiveLabel extends Label
{
	/**
	 * @param el
	 */
	public MouseSensitiveLabel(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.web.ui.Label#textIs(java.lang.String)
	 */
	@Override
	public String text()
	{
		return super.text();
	}
}
