/**
 * Copyright (c) (2016-2017),VSI and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.widget;

import org.openqa.selenium.WebElement;

import com.dsc.test.common.Context;
import com.dsc.test.common.ui.UIObject;


/**
 * The Interface PopupPanel.
 *
 * @Author alex
 * @CreateTime Mar 17, 2014 9:35:04 AM
 * @Version 1.0
 * @Since 1.0
 */
public class PopupPanel extends UIObject
{

	/**
	 * @param browser
	 * @param wrapee
	 */
	//	public PopupPanel(Context context, WebElement wrapee)
	//	{
	//		super(context, wrapee);
	//	}

	/**
	 * @param wrapee
	 */
	public PopupPanel(Context context,WebElement wrapee)
	{
		super(context,wrapee);
	}

	@Override
	public boolean isHidden()
	{
		return context().ensureNotPresented(annotatedId);
	}

	boolean isShowing()
	{
		return !isHidden();
	}
}