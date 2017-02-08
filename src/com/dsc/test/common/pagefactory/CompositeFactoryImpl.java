/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.pagefactory;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.ui.widget.Composite;
import com.dsc.test.web.Browser;

public class CompositeFactoryImpl implements CompositeFactory
{

	@Override
	public <T extends Composite> T create(Browser browser,Class<T> wrappingClass, WebElement wrapee)
	{
		try
		{
			return wrappingClass.getDeclaredConstructor(browser.getClass(),WebElement.class).newInstance(browser,wrapee);
		}catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
