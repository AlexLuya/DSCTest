/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.widget;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;


/**
 * The Class InputSelectBox.
 *
 * @Author alex
 * @CreateTime Aug 26, 2014 7:56:42 PM
 * @Version 1.0
 * @Since v1.0
 */
public class InputSelectBox extends SuggestionBox
{
	MouseSensitiveLabel	label;
	PopupPanel			selectorIndicator;
	/**
	 * @param el
	 */
	public InputSelectBox(Browser browser,WebElement wrapee)
	{
		super(browser,wrapee);
	}
}