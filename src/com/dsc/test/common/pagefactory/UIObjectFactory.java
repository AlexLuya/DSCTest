/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.pagefactory;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.ui.UIObject;
import com.dsc.test.common.TesteeHost;

public interface UIObjectFactory
{
	public <E extends UIObject> E create(TesteeHost browser, Class<E> containerClass, WebElement wrapeeElement) throws Exception;
}
