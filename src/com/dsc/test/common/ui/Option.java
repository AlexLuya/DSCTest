/**
 * Copyright (c) (2010-2018),Deep Space Century and/or its affiliates.All rights reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;

/**
 * @Author alex
 * @Description list box's option
 * @CreateTime Dec 28, 2016 11:18:21 AM
 * @Version 1.0
 * @Since 1.0
 */
public class Option extends UIObject
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	public Option(Context<? ,?> context, WebElement wrapee)
	{
		super(context, wrapee);
	}

	/**
	 * @param id
	 * @return
	 */
	public boolean idEquals(String id)
	{
		return id.equals(id());
	}

	public boolean isSelected(){
		return wrapee.isSelected();
	}

	@Override
	public String text()
	{
		return super.text();
	}
}
