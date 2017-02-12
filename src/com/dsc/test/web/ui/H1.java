/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.web.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

/**
 * @Author alex
 * @CreateTime Dec 27, 2016 4:35:28 PM
 * @Version 1.0
 * @Since 1.0
 */
public class H1 extends H
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	public H1(Context<? ,?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	/* (non-Javadoc)
	 * @see com.dsc.test.common.ui.H#level()
	 */
	@Override
	public int level()
	{
		return 1;
	}

}
