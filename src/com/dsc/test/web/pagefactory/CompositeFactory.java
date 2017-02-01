/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.pagefactory;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;
import com.dsc.test.web.ui.gwt.Composite;

public interface CompositeFactory
{
	public <T extends Composite> T create(Browser browser, Class<T> wrappingClass, WebElement wrapeeElement);
}
