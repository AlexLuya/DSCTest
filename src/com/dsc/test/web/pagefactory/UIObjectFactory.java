/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.pagefactory;

import org.openqa.selenium.WebElement;

import com.dsc.test.web.Browser;
import com.dsc.test.web.ui.UIObject;

public interface UIObjectFactory
{
	public <E extends UIObject> E create(Browser browser, Class<E> containerClass, WebElement wrapeeElement) throws Exception;
}
