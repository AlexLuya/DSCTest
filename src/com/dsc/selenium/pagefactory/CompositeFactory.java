/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.selenium.pagefactory;

import org.openqa.selenium.WebElement;

import com.dsc.selenium.Browser;
import com.dsc.selenium.ui.gwt.Composite;

public interface CompositeFactory
{
	public <T extends Composite> T create(Browser browser, Class<T> wrappingClass, WebElement wrapeeElement);
}
