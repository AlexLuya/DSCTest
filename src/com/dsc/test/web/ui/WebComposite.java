/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.ui.UIObject;
import com.dsc.test.common.ui.widget.Composite;
import com.dsc.test.web.Browser;

public abstract class WebComposite extends Composite<Browser>
{

	public WebComposite(Browser browser, String id)
	{
		super(browser, id);
	}

	public WebComposite(Browser browser, UIObject object){
		super(browser,object);
	}

	public WebComposite(Browser browser, WebElement wrapee)
	{
		super(browser, wrapee);
	}
}