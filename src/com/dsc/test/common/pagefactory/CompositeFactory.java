/**
 * Copyright (c) (2016-2017),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.pagefactory;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.web.ui.WebComposite;

public class CompositeFactory
{

	public <T extends WebComposite> T create(Context<? ,?> context,Class<T> wrappingClass, WebElement wrapee)
	{
		try
		{
			return wrappingClass.getDeclaredConstructor(context.getClass(),WebElement.class).newInstance(context,wrapee);
		}catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
