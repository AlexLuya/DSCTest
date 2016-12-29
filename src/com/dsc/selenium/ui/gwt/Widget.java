/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.ui.gwt;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.dsc.selenium.Browser;
import com.dsc.selenium.pagefactory.WebElementDecorator;
import com.dsc.selenium.ui.UIObject;

/**
 * @Author alex
 * @CreateTime Jan 5, 2015 11:15:04 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Widget extends UIObject
{
	public Widget(Browser browser, String containerId)
	{
		this(browser, browser.findElemById(containerId));
	}

	public Widget(Browser browser, UIObject wrapee){
		this(browser,wrapee.element());
	}

	public Widget(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
		// initialize all fields
		PageFactory.initElements(new WebElementDecorator(browser,this.element()), this);
	}
}
